package application.viewpagerdemo.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import application.viewpagerdemo.R;
import application.viewpagerdemo.R2;
import application.viewpagerdemo.TabItem;
import application.viewpagerdemo.ui.fragment.TabFragment;
import application.viewpagerdemo.util.ViewPagerConstant;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ViewPagerConstant.FragmentsActivity)
public class FragmentsActivity extends BaseActivity {

    @BindView(R2.id.content)
    FrameLayout content;

    @BindView(R2.id.tab_content)
    LinearLayout tab_content;

    private String[] titles = {"第一个", "第二个", "第三个", "第四个", "第五个"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<TabItem> tabItemList = new ArrayList<>();

    private int lastSelectedIndex = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragments;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;

        int length = titles.length;
        for (int index = 0; index < length; index++) {
            addFragments(titles[index]);
            TabItem tabItem = new TabItem(this, R.drawable.tab_wechat, R.drawable.tab_wechat_selected, titles[index], layoutParams,
                    getResources().getColor(R.color.tab_wx_text_color), getResources().getColor(R.color.tab_wx_text_color_selected)
            );
            tabItemList.add(tabItem);
            tab_content.addView(tabItem.getView());
            if (index == 0) {
                tabItem.setSelected(true);
            }

            final int aa = index;
            tabItem.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e("点击到了底部导航栏==" + aa);
                    switchFragment(aa);
                }

            });
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content, fragmentList.get(0), titles[0]).commit();
    }


    private void addFragments(String title) {
        TabFragment tabFragment = TabFragment.newInstance(title, "3");

        fragmentList.add(tabFragment);
    }

    private void switchFragment(int index) {
        if (index != lastSelectedIndex) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragmentList.get(lastSelectedIndex));
            if (!fragmentList.get(index).isAdded()) {
                transaction.add(R.id.content, fragmentList.get(index), titles[index]);
            }
            transaction.show(fragmentList.get(index)).commit();

            tabItemList.get(lastSelectedIndex).setSelected(false);
            tabItemList.get(index).setSelected(true);
            lastSelectedIndex = index;
        }
    }

}
