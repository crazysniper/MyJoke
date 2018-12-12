package application.scrollerdemo.ui;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.scrollerdemo.R;
import application.scrollerdemo.util.ScrollerConstant;

@Route(path = ScrollerConstant.ScrollerMainActivity)
public class ScrollerMainActivity extends BaseActivity implements View.OnClickListener {

    private Button scrollerTo, scrollerBy, scrollerToZheng, scrollerByZheng, getInfo;
    private TextView textView;
    private LinearLayout parent;

    private int rightBottomScrollerTo, rightBottomScrollerBy, leftTopScrollerTo, leftTopScrollerBy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroller_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        scrollerTo = findView(R.id.scrollerTo);
        scrollerBy = findView(R.id.scrollerBy);
        scrollerToZheng = findView(R.id.scrollerToZheng);
        scrollerByZheng = findView(R.id.scrollerByZheng);
        parent = findView(R.id.parent);
        getInfo = findView(R.id.getInfo);
        textView = findView(R.id.textView);

        scrollerTo.setOnClickListener(this);
        scrollerBy.setOnClickListener(this);
        scrollerToZheng.setOnClickListener(this);
        scrollerByZheng.setOnClickListener(this);
        getInfo.setOnClickListener(this);
        textView.setOnClickListener(this);

        rightBottomScrollerTo = getResources().getDimensionPixelOffset(R.dimen.scrollTofu);
        rightBottomScrollerBy = getResources().getDimensionPixelOffset(R.dimen.scrollByfu);


        leftTopScrollerTo = getResources().getDimensionPixelOffset(R.dimen.scrollTozheng);
        leftTopScrollerBy = getResources().getDimensionPixelOffset(R.dimen.scrollByzheng);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == scrollerTo.getId()) { // 向右下角scrollerTo
            parent.scrollTo(-rightBottomScrollerTo, -rightBottomScrollerTo);
        } else if (v.getId() == scrollerBy.getId()) { // 向右下角scrollerBy
            parent.scrollBy(-rightBottomScrollerBy, -rightBottomScrollerBy);
        } else if (v.getId() == scrollerToZheng.getId()) { // 向左上角scrollerTo
            parent.scrollTo(leftTopScrollerTo, leftTopScrollerTo);
        } else if (v.getId() == scrollerByZheng.getId()) { // 向左上角scrollerBy
            parent.scrollBy(leftTopScrollerBy, leftTopScrollerBy);
        } else if (v.getId() == R.id.getInfo) {

        } else if (v.getId() == R.id.textView) {
            ToastUtil.getInstance().showToast(ScrollerMainActivity.this, "点击到了");
        }
    }
}
