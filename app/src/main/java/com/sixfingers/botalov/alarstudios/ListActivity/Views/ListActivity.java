package com.sixfingers.botalov.alarstudios.ListActivity.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sixfingers.botalov.alarstudios.R;
import com.sixfingers.botalov.alarstudios.ListActivity.Models.ResponseEntity;
import com.sixfingers.botalov.alarstudios.ListActivity.Presenters.ListDataPresenter;

public class ListActivity extends AppCompatActivity implements IListView {

    private ProgressBar progressBar;
    private ListAdapter adapter;

    private ListDataPresenter presenter;
    private String code;

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        presenter = new ListDataPresenter();
        presenter.attachView(this);

        initViews();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initViews(){
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    getData(currentPage);
                }
            }
        });

        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        Bundle args = getIntent().getExtras();
        if(args != null){
            code = args.getString(CODE_ARG);
            if(code != null){
                getData(currentPage);
            }
        }
    }

    @Override
    public void updateView(ResponseEntity response) {
        adapter.appendData(response.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData(int page) {
        if(presenter != null){
            presenter.onLoadData(code, page);
            currentPage++;
        }
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }
}
