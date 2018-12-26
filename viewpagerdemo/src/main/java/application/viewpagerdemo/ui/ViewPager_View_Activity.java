package application.viewpagerdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.myjoke.baselibray.base.BaseActivity;

public class ViewPager_View_Activity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(ViewPager_View_Activity.this);
    }

    @Override
    public void initData() {

    }
}
