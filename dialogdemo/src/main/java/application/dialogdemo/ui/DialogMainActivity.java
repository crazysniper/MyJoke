package application.dialogdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.util.ToastUtil;

import application.dialogdemo.R;
import application.dialogdemo.dialog.MyDialog;
import application.dialogdemo.dialog.MyDialogListener;
import application.dialogdemo.pop.MyPopupWindow;
import application.dialogdemo.utils.DialogConstant;

@Route(path = DialogConstant.DialogMainActivity)
public class DialogMainActivity extends AppCompatActivity {

    private Button openDialog, openPopupWindow, openServiceDialog;
    private LinearLayout activity_dialog_main;
    private MyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_main);
        ARouter.getInstance().inject(this);

        openDialog = (Button) findViewById(R.id.openDialog);
        openPopupWindow = (Button) findViewById(R.id.openPopupWindow);
        openServiceDialog = (Button) findViewById(R.id.openServiceDialog);
        activity_dialog_main = (LinearLayout) findViewById(R.id.activity_dialog_main);

        initDialog();

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        openServiceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        openPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyPopupWindow(DialogMainActivity.this).showAtLocation(openPopupWindow.getRootView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    public void initDialog() {
        dialog = new MyDialog.Builder(DialogMainActivity.this, R.style.DialogTheme)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setTitle("标题")
                .setContent("内容")
                .setPositiveButton("很好", new MyDialogListener.OnClickListener() {
                    @Override
                    public void onClick() {
                        ToastUtil.getInstance().showToast(DialogMainActivity.this, "很好");
                    }
                })
                .setNegativeButton("不好", new MyDialogListener.OnCancelledListener() {
                    @Override
                    public void onCancel() {
                        ToastUtil.getInstance().showToast(DialogMainActivity.this, "不好");
                    }
                })
                .create();
    }
}
