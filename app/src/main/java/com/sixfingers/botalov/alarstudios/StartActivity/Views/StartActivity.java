package com.sixfingers.botalov.alarstudios.StartActivity.Views;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sixfingers.botalov.alarstudios.R;
import com.sixfingers.botalov.alarstudios.StartActivity.Presenters.IStartPresenter;
import com.sixfingers.botalov.alarstudios.StartActivity.Presenters.StartPresenter;

public class StartActivity extends AppCompatActivity implements IStartView {

    private IStartPresenter presenter;

    private ProgressBar progressBar;
    private TextInputEditText userNameEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initViews();
        presenter = new StartPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.detachView();
        }
    }

    private void initViews(){
        progressBar = findViewById(R.id.login_progress_bar);
        userNameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v->presenter.onLogin());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getUserName() {
        return userNameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
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
