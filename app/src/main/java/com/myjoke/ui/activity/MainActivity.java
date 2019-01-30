package com.myjoke.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.annotations.MyBindView;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.base.BaseFragment;
import com.myjoke.baselibray.util.ToastUtil;
import com.myjoke.ui.fragment.ActivityFragment;
import com.myjoke.ui.fragment.HomeFragment;
import com.myjoke.ui.fragment.MessageFragment;
import com.myjoke.ui.fragment.MyFragment;
import com.myjoke.util.ConstantPath;

import java.util.ArrayList;
import java.util.List;

import application.newsmodule.fragment.NewsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ConstantPath.MainActivity)
public class MainActivity extends BaseActivity {

    private long currentTime = 0;

    @BindView(android.R.id.tabhost)
    FragmentTabHost tabHost;

    @MyBindView(R.id.linearLayout)
    LinearLayout linearLayout2;

    @MyBindView(android.R.id.tabhost)
    FragmentTabHost tabHost2;

    private List<TabItem> tabItemList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Log.e("MyBind", "开始绑定");
//        MyBind.bind(this);
        ARouter.getInstance().inject(this);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.getTabWidget().setDividerDrawable(null);

        initTabList();

        int size = tabItemList.size();
        for (int index = 0; index < size; index++) {
            TabItem tabItem = tabItemList.get(index);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabItem.getTitle()).setIndicator(tabItem.getTabView());
            tabHost.addTab(tabSpec, tabItem.getFragment(), tabItem.getBundle());

            tabHost.getTabWidget().getChildAt(index).setBackgroundColor(getResources().getColor(R.color.tab_bg_color));
            if (index == 0) {
                tabItem.setSelected(true);
            }
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (TabItem tabItem : tabItemList) {
                    if (tabId.equals(tabItem.getTitle())) {
                        tabItem.setSelected(true);
                    } else {
                        tabItem.setSelected(false);
                    }
                }
            }
        });
    }

    public void initTabList() {
        // 首页
        TabItem tabItem = new TabItem(HomeFragment.class, R.drawable.tab_home, R.drawable.tab_home_selected,
                getResources().getString(R.string.tab_title_home));
        tabItemList.add(tabItem);


        // 头条
        tabItem = new TabItem(HomeFragment.class, R.drawable.tab_home, R.drawable.tab_home_selected,
                getResources().getString(R.string.tab_title_toutiao));
        tabItemList.add(tabItem);

        // 新闻
        tabItem = new TabItem(NewsFragment.class, R.drawable.tab_news, R.drawable.tab_news_selected,
                getResources().getString(R.string.tab_title_news));
        tabItemList.add(tabItem);

        // 活动
        tabItem = new TabItem(ActivityFragment.class, R.drawable.tab_activity, R.drawable.tab_activity_selected,
                getResources().getString(R.string.tab_title_activity));
        tabItemList.add(tabItem);

        // 消息
        tabItem = new TabItem(MessageFragment.class, R.drawable.tab_message, R.drawable.tab_message_selected,
                getResources().getString(R.string.tab_title_message));
        tabItemList.add(tabItem);


        // 我的
        tabItem = new TabItem(MyFragment.class, R.drawable.tab_my, R.drawable.tab_my_selected,
                getResources().getString(R.string.tab_title_my));
        tabItemList.add(tabItem);

        tabItem = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData() {

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        LogUtil.e("MainActivity requestCode=" + requestCode + "   resultCode=" + resultCode + "   data=" + data.getExtras().toString());
//        if (requestCode == 100 && resultCode == 200) {
//            Toast.makeText(MainActivity.this, "MainActivity result==" + data.getStringExtra("resultKey"), Toast.LENGTH_SHORT).show();
//        }
//    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime > 1500) {
            ToastUtil.getInstance().showToast(this, "连续点2次退出");
            currentTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    class TabItem {
        private int imageNormal;
        private int imageSelected;
        private String title;
        private Class<? extends BaseFragment> fragment;
        private ImageView tabImg;
        private TextView tabTitle;
        private Bundle bundle;
        private View view;

        public TabItem(Class<? extends BaseFragment> fragment, int imageNormal, int imageSelected, String title) {
            this.fragment = fragment;
            this.imageNormal = imageNormal;
            this.imageSelected = imageSelected;
            this.title = title;
        }

        public Class<? extends BaseFragment> getFragment() {
            return fragment;
        }

        public void setFragment(Class<? extends BaseFragment> fragment) {
            this.fragment = fragment;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public void setImageNormal(int imageNormal) {
            this.imageNormal = imageNormal;
        }

        public int getImageSelected() {
            return imageSelected;
        }

        public void setImageSelected(int imageSelected) {
            this.imageSelected = imageSelected;
        }

        public void setSelected(boolean selected) {
            if (selected) {
                tabTitle.setTextColor(getColor(R.color.tab_title_selected_color));
                tabImg.setImageResource(imageSelected);
            } else {
                tabTitle.setTextColor(getColor(R.color.tab_title_default_color));
                tabImg.setImageResource(imageNormal);
            }
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Bundle getBundle() {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return bundle;
        }

        public View getTabView() {
            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_tab_item, null);
                tabImg = (ImageView) view.findViewById(R.id.tabImg);
                tabTitle = (TextView) view.findViewById(R.id.tabTitle);

                tabImg.setImageResource(imageNormal);
                tabTitle.setText(title);
            }
            return view;
        }
    }
}
