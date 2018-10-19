package com.myjoke.baselibray.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Gao on 2018/10/5.
 */

public class BaseActivity extends AppCompatActivity {

    protected <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

}
