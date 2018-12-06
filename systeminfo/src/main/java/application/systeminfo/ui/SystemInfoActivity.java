package application.systeminfo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import application.systeminfo.R;
import application.systeminfo.util.SystemInfoConstant;
import application.systeminfo.util.TelephoneUtil;

@Route(path = SystemInfoConstant.SystemInfoActivity)
public class SystemInfoActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        ARouter.getInstance().inject(this);

        textView = (TextView) findViewById(R.id.textView);

        textView.setText(TelephoneUtil.getPhoneInfo(this).toString());
    }
}
