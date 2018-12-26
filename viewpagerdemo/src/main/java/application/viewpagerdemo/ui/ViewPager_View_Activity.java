package application.viewpagerdemo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.viewpagerdemo.R;
import application.viewpagerdemo.util.ViewPagerConstant;

@Route(path = ViewPagerConstant.ViewPager_View_Activity)
public class ViewPager_View_Activity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager_view;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        LayoutInflater layoutInflater = getLayoutInflater();

        View view1 = layoutInflater.inflate(R.layout.item, null);
        TextView textView= view1.findViewById(R.id.textView);

    }

    @Override
    public void initData() {

    }
}
