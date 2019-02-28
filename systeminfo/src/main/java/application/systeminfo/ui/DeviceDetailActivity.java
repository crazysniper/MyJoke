package application.systeminfo.ui;

import android.graphics.Rect;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.baseview.layout.MyLinearView;
import application.systeminfo.R;
import application.systeminfo.R2;
import application.systeminfo.util.SystemInfoConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = SystemInfoConstant.DeviceDetailActivity)
public class DeviceDetailActivity extends BaseActivity {

    @BindView(R2.id.deviceWidth)
    MyLinearView deviceWidth;
    @BindView(R2.id.deviceHeight)
    MyLinearView deviceHeight;


    @BindView(R2.id.appWidth)
    MyLinearView appWidth;
    @BindView(R2.id.appHeight)
    MyLinearView appHeight;

    @BindView(R2.id.statusBarHeight)
    MyLinearView statusBarHeight;

    @BindView(R2.id.getInfo)
    Button getInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_device_detail;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        deviceWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().showToast("1111222");
            }
        });
    }

    @OnClick({R2.id.getInfo, R2.id.deviceHeight, R2.id.appWidth, R2.id.appHeight, R2.id.statusBarHeight})
    public void onClick(View v) {
        ToastUtil.getInstance().showToast("1111");
        if (v.getId() == R.id.getInfo) {
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            deviceWidth.setRightText("" + widthPixels);

            int heightPixels = getResources().getDisplayMetrics().heightPixels;
            deviceHeight.setRightText("" + heightPixels);


            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);




            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int statusHeight = rect.top;
            statusBarHeight.setRightText("" + statusHeight);


        }
    }
}
