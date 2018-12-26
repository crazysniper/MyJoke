package application.viewpagerdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.myjoke.baselibray.base.BaseActivity;

public class ViewPagerMainActivity extends BaseActivity {

    private Button btn1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager_main;
    }

    @Override
    public void initView() {
        btn1 = findView(R.id.btn1);
    }

    @Override
    public void initData() {

    }
}
