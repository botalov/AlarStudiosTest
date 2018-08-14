package com.sixfingers.botalov.alarstudios.StartActivity.Presenters;

import android.content.Intent;
import android.os.Bundle;

import com.sixfingers.botalov.alarstudios.App;
import com.sixfingers.botalov.alarstudios.R;
import com.sixfingers.botalov.alarstudios.ListActivity.Views.ListActivity;
import com.sixfingers.botalov.alarstudios.StartActivity.Models.LoginEntity;
import com.sixfingers.botalov.alarstudios.StartActivity.Presenters.Network.ILoginNetwork;
import com.sixfingers.botalov.alarstudios.StartActivity.Presenters.Network.LoginNetworkClient;
import com.sixfingers.botalov.alarstudios.StartActivity.Views.IStartView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class StartPresenter implements IStartPresenter {
    private IStartView view;

    @Override
    public void attachView(IStartView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onLogin() {
        String username = view.getUserName();
        String password = view.getPassword();

        view.showLoader();

        LoginNetworkClient networkClient = new LoginNetworkClient();
        Retrofit retrofit = networkClient.getRetrofit();

        Observable<LoginEntity> observableLogin = retrofit.create(ILoginNetwork.class)
                .getCode(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observableLogin.subscribeWith(getObserver());

    }

    private DisposableObserver<LoginEntity> getObserver() {
        return new DisposableObserver<LoginEntity>() {

            @Override
            public void onNext(LoginEntity loginEntity) {
                String status = loginEntity.getStatus();
                final String errorStatus = App.getContext().getString(R.string.error_status);

                if(status.equals(errorStatus)){
                    view.showError(App.getContext().getString(R.string.login_password_error));
                }
                else{
                    Bundle args = new Bundle();
                    args.putString(ListActivity.CODE_ARG, loginEntity.getCode());

                    Intent intent = new Intent(App.getContext(), ListActivity.class);
                    intent.putExtras(args);
                    App.getContext().startActivity(intent);
                }
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
