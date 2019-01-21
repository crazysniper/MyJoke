package com.myjoke.ui.activity.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.SpUtil;
import com.myjoke.baselibray.util.ToastUtil;
import com.myjoke.bean.User;
import com.myjoke.contact.LoginContact;
import com.myjoke.contact.LoginContact.LoginView;
import com.myjoke.presenter.LoginPresenterImpl;
import com.myjoke.util.ConstantPath;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ConstantPath.LoginActivity)
public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    LoginContact.LoginPresenter loginPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        loginPresenter = new LoginPresenterImpl();
        loginPresenter.attachView(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                User user = new User(etName.getText().toString(), etPwd.getText().toString());
                loginPresenter.login(user);
                break;
        }
    }

    @Override
    public boolean isNameValid() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPwdValid() {
        if (TextUtils.isEmpty(etPwd.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.getInstance().showToast(msg);
    }

    @Override
    public void loginSuccess() {
        SpUtil.put("isLogined", true);
        showToast("登陆成功");
        ARouter.getInstance().build(ConstantPath.MainActivity).navigation();
        finish();
    }

    @Override
    public void loginFailed() {
        showToast("登陆失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView(this);
    }
}
