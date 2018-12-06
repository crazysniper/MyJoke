package application.supportdesignview.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.supportdesignview.R;
import application.supportdesignview.util.SupportDesignConstant;

@Route(path = SupportDesignConstant.ConstraintLayoutActivity)
public class ConstraintLayoutActivity extends BaseActivity implements View.OnClickListener {

    TextView tv2;
    Button btn1, btn2, btn3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        tv2 = (TextView) findViewById(R.id.tv2);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            tv2.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.btn2) {
            tv2.setVisibility(View.GONE);
        } else if (v.getId() == R.id.btn3) {
            tv2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initData() {

    }
}
