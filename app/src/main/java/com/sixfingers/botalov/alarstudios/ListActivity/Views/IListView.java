package com.sixfingers.botalov.alarstudios.ListActivity.Views;

import com.sixfingers.botalov.alarstudios.ListActivity.Models.ResponseEntity;

public interface IListView {
    String CODE_ARG = "codeArg";

    void updateView(ResponseEntity response);
    void showError(String error);
    void getData(int page);

    void hideLoader();

    void showLoader();
}
