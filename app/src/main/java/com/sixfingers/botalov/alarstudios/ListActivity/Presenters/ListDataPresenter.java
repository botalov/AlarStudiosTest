package com.sixfingers.botalov.alarstudios.ListActivity.Presenters;

import com.sixfingers.botalov.alarstudios.ListActivity.Models.ResponseEntity;
import com.sixfingers.botalov.alarstudios.ListActivity.Presenters.Network.IListDataNetwork;
import com.sixfingers.botalov.alarstudios.ListActivity.Presenters.Network.ListDataNetworkClient;
import com.sixfingers.botalov.alarstudios.ListActivity.Views.IListView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ListDataPresenter implements IListDataPresenter {

    private IListView view;
    private ListDataNetworkClient networkClient = new ListDataNetworkClient();
    private Retrofit retrofit = networkClient.getRetrofit();

    @Override
    public void attachView(IListView view){
        this.view = view;
    }
    @Override
    public void detachView(){
        this.view = null;
    }

    @Override
    public void onLoadData(String code, int page) {
        view.showLoader();
        Observable<ResponseEntity> observableLogin = retrofit.create(IListDataNetwork.class)
                .getData(code, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observableLogin.subscribeWith(getObserver());
    }

    private DisposableObserver<ResponseEntity> getObserver() {
        return new DisposableObserver<ResponseEntity>() {

            @Override
            public void onNext(ResponseEntity responseEntity) {
                view.updateView(responseEntity);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoader();
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                view.hideLoader();
            }
        };
    }
}
