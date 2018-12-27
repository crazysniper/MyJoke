package application.systeminfo.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;
import application.systeminfo.util.TelephoneUtil;

/**
 * Created by Gao on 2018/12/27.
 */

@Route(path = SystemInfoConstant.SystemInfoActivity)
public class SystemInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView textView;
    private Button ask;

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_info;
    }

    private String[] permissions;

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        textView = findView(R.id.textView);
        ask = findView(R.id.ask);

        permissions = new String[]{Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE};

        ask.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            ToastUtil.getInstance().showToast(SystemInfoActivity.this, "6.0及以上版本，需要动态申请权限");
        } else {
            hasPermission();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ask) {
            checkPermission();
        }
    }

    private void hasPermission() {
        textView.setText(TelephoneUtil.getPhoneInfo(this).toString());
    }

    private void checkPermission() {
        List<String> list = new ArrayList<>();
        if (permissions != null) {
            for (String permission : permissions) {
                int checkPermission = ContextCompat.checkSelfPermission(this, permission);
                if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                    list.add(permission);
                }
            }
        }

        if (list.size() > 0) {
            ActivityCompat.requestPermissions(this, list.toArray(new String[list.size()]), 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermission = true;
        switch (requestCode) {
            case 100:
                for (int index = 0; index < grantResults.length; index++) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        ToastUtil.getInstance().showToast(SystemInfoActivity.this, permissions[index] + "被拒绝了");
                        hasPermission = false;
                    }
                }
                if (hasPermission) {
                    hasPermission();
                }
                break;
        }
    }
}
