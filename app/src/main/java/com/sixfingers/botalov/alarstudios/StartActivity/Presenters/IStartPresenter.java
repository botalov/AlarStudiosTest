package com.sixfingers.botalov.alarstudios.StartActivity.Presenters;

import com.sixfingers.botalov.alarstudios.StartActivity.Views.IStartView;

public interface IStartPresenter {

    void attachView(IStartView view);
    void detachView();

    void onLogin();
}
