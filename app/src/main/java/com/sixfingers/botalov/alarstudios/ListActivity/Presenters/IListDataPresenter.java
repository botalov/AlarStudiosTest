package com.sixfingers.botalov.alarstudios.ListActivity.Presenters;

import com.sixfingers.botalov.alarstudios.ListActivity.Views.IListView;

public interface IListDataPresenter {
    void attachView(IListView view);
    void detachView();

    void onLoadData(String code, int page);
}
