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

    private TextView textView, runtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        ARouter.getInstance().inject(this);

        textView = (TextView) findViewById(R.id.textView);
        runtime = (TextView) findViewById(R.id.runtime);

        textView.setText(TelephoneUtil.getPhoneInfo(this).toString());

        long freeMemory = Runtime.getRuntime().freeMemory(); // 返回的单位是字节
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();

        runtime.setText("freeMemory=" + 1.00 * freeMemory / 1024 / 1024 + "M\n"
                + "totalMemory=" + 1.00 * totalMemory / 1024 / 1024 + "M\n"
                + "maxMemory=" + 1.00 * maxMemory / 1024 / 1024 + "M\n");
    }
}
