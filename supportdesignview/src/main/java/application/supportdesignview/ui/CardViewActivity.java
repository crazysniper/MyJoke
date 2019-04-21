package application.supportdesignview.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import application.supportdesignview.R;
import application.supportdesignview.R2;
import application.supportdesignview.util.SupportDesignConstant;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = SupportDesignConstant.CardViewActivity)
public class CardViewActivity extends BaseActivity {

    @BindView(R2.id.toolBar)
    Toolbar toolBar;
    @BindView(R2.id.drawlayout)
    DrawerLayout drawlayout;

    @BindView(R2.id.nav_view)
    NavigationView navView;
    @BindView(R2.id.fab)
    FloatingActionButton fab;

    Snackbar snackbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        setSupportActionBar(toolBar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 让导航按钮显示出来。
            actionBar.setHomeAsUpIndicator(R.drawable.news); // 设置导航图标
        }

        navView.setCheckedItem(R.id.call); // 设置默认选中的

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                return false;
                drawlayout.closeDrawers();
                ToastUtil.getInstance().showToast(menuItem.getTitle().toString());
                return true;
            }
        });

        snackbar = Snackbar.make(toolBar, "Snakebar", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().showToast("点击了确定");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!snackbar.isShown()) {
                    snackbar.show();
                }

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.backup) {
            ToastUtil.getInstance().showToast("备份");
        } else if (item.getItemId() == R.id.delete) {
            ToastUtil.getInstance().showToast("删除");
        } else if (item.getItemId() == R.id.setting) {
            ToastUtil.getInstance().showToast("设置");
        } else if (item.getItemId() == android.R.id.home) {
            drawlayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
