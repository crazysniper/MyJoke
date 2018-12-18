package application.dialogdemo.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;

import application.dialogdemo.R;
import application.dialogdemo.dialog.MyDialog;
import application.dialogdemo.dialog.MyDialogListener;
import application.dialogdemo.pop.MyPopupWindow;
import application.dialogdemo.utils.DialogConstant;

@Route(path = DialogConstant.DialogMainActivity)
public class DialogMainActivity extends BaseActivity {

    private Button openDialog, openPopupWindow, openServiceDialog, openAction;
    private LinearLayout activity_dialog_main;
    private MyDialog dialog;

    private EditText phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        openDialog = (Button) findViewById(R.id.openDialog);
        openPopupWindow = (Button) findViewById(R.id.openPopupWindow);
        openServiceDialog = (Button) findViewById(R.id.openServiceDialog);
        openAction = (Button) findViewById(R.id.openAction);
        activity_dialog_main = (LinearLayout) findViewById(R.id.activity_dialog_main);
        phone = findView(R.id.phone);

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

        openAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(DialogConstant.ActionActivity).navigation();
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.toString().length();
                //删除数字
                if (count == 0) {
                    if (length == 4) {
                        phone.setText(s.subSequence(0, 3));
                    }
                    if (length == 9) {
                        phone.setText(s.subSequence(0, 8));
                    }
                }
                //添加数字
                if (count == 1) {
                    if (length == 4) {
                        String part1 = s.subSequence(0, 3).toString();
                        String part2 = s.subSequence(3, length).toString();
                        phone.setText(part1 + " " + part2);
                    }
                    if (length == 9) {
                        String part1 = s.subSequence(0, 8).toString();
                        String part2 = s.subSequence(8, length).toString();
                        phone.setText(part1 + " " + part2);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //将光标移动到末尾
                phone.setSelection(phone.getText().toString().length());
                //处理s
            }
        });

        phone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LogUtil.e("actionId="+actionId);
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ToastUtil.getInstance().showToast(DialogMainActivity.this, phone.getText().toString());
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {
        initDialog();
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
