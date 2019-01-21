package com.myjoke.presenter;

import com.myjoke.baselibray.base.view.BaseView;
import com.myjoke.bean.User;
import com.myjoke.contact.LoginContact;
import com.myjoke.model.LoginModel;

/**
 * Created by Gao on 2019/1/21.
 */

public class LoginPresenterImpl implements LoginContact.LoginPresenter {

    private LoginContact.LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl() {
        this.loginModel = new LoginModel(this);
    }

    @Override
    public void login(User user) {
        if (loginView != null) {
            if (!loginView.isPwdValid() || !loginView.isNameValid()) {
                loginView.showToast("用户名或密码不能为空");
            } else {
                this.loginModel.login(user);
            }
        }
    }

    @Override
    public void loginSuccess() {
        if (loginView != null) {
            loginView.loginSuccess();
        }
    }

    @Override
    public void loginFailed() {
        if (loginView != null) {
            loginView.loginFailed();
        }
    }

    @Override
    public void attachView(BaseView view) {
        this.loginView = (LoginContact.LoginView) view;
    }

    @Override
    public void detachView(BaseView view) {
        this.loginView = null;
    }


}
