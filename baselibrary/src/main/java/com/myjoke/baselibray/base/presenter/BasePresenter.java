package com.myjoke.baselibray.base.presenter;

import com.myjoke.baselibray.base.view.BaseView;

/**
 * Created by Gao on 2019/1/21.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView(T view);
}
