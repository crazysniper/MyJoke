package com.myjoke.model;

import com.myjoke.bean.User;
import com.myjoke.contact.LoginContact;

/**
 * Created by Gao on 2019/1/21.
 */

public class LoginModel {

    private LoginContact.LoginPresenter loginPresenter;

    public LoginModel(LoginContact.LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void login(User user) {
        if ("1".equals(user.getName()) && "1".equals(user.getPwd())) {
            loginPresenter.loginSuccess();
        } else {
            loginPresenter.loginFailed();
        }
    }

}
