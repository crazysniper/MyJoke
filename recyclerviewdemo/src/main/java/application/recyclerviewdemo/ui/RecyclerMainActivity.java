package application.recyclerviewdemo.ui;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.R2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerMainActivity extends BaseActivity {

    @BindView(R2.id.activity_recycler_main)
    LinearLayout relativeLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_main;
    }

    @Override
        public void initView() {
            ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        LogUtil.e("RecyclerViewDemo Module packageName==" + getPackageName());
//        findViewById(R.id.recycler).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("1111111", "333333");
//                Toast.makeText(RecyclerMainActivity.this, "RecycerView22", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @OnClick({R2.id.recycler})
    public void click(View view) {
        if (R.id.recycler == view.getId()) {
            Log.e("1111111", "2222222222222222222");
            Toast.makeText(this, "RecycerView", Toast.LENGTH_SHORT).show();
        }
    }

}
