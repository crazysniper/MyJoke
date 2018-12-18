package application.materialdemo.ui;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.materialdemo.R;
import application.materialdemo.util.MaterialConstant;

@Route(path = MaterialConstant.MaterialMainActivity)
public class MaterialMainActivity extends BaseActivity implements View.OnClickListener {

    private Button cardView, snackBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_material_main;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        cardView = findView(R.id.cardView);
        snackBar = findView(R.id.snackBar);

        cardView.setOnClickListener(this);
        snackBar.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardView) {
            ARouter.getInstance().build(MaterialConstant.CardViewActivity).navigation();
        } else if (v.getId() == R.id.snackBar) {
            ARouter.getInstance().build(MaterialConstant.SnackBarActivity).navigation();
        }
    }
}
