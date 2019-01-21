package com.myjoke.contact;

import com.myjoke.baselibray.base.presenter.BasePresenter;
import com.myjoke.baselibray.base.view.BaseView;
import com.myjoke.bean.User;

/**
 * Created by Gao on 2019/1/21.
 */

public interface LoginContact {

    interface LoginPresenter extends BasePresenter<BaseView> {
        void login(User user);

        void loginSuccess();

        void loginFailed();

    }

    interface LoginView extends BaseView {
        boolean isNameValid();

        boolean isPwdValid();

        void showToast(String msg);

        void loginSuccess();

        void loginFailed();


    }

}
