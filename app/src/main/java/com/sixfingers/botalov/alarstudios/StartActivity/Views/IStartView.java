package com.sixfingers.botalov.alarstudios.StartActivity.Views;

public interface IStartView {

    void showError(String error);
    String getUserName();
    String getPassword();

    void showLoader();
    void hideLoader();

}
