package application.viewpagerdemo.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import application.viewpagerdemo.R;
import application.viewpagerdemo.TabItem;
import application.viewpagerdemo.adapter.ViewFragmentPagerAdapter;
import application.viewpagerdemo.ui.fragment.TabFragment;
import application.viewpagerdemo.util.ViewPagerConstant;

@Route(path = ViewPagerConstant.ViewPager_Fragment_Activity)
public class ViewPager_Fragment_Activity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private LinearLayout tab_content;
    private View line;

    private static final String TAG = "ViewPager_View_Activity";
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<TabItem> tabItemList = new ArrayList<>();
    private String[] titles = {"第一个", "第二个", "第三个", "第四个", "第五个"};

    private int lastSelectedIndex = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_pager__fragment;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        viewPager = findView(R.id.viewPager);
        tab_content = findView(R.id.tab_content);
        line = findView(R.id.line);

        int screenWidth = ScreenUtil.getScreenWidth(this);
        final int width = screenWidth / titles.length;


        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

        line.setLayoutParams(new LinearLayout.LayoutParams(width, ScreenUtil.dip2px(this, 2)));

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
                    viewPager.setCurrentItem(aa, false);
                }
            });
        }
        ViewFragmentPagerAdapter adapter = new ViewFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.e(TAG, "onPageScrolled  position=" + position + "     positionOffset=" + positionOffset + "   positionOffsetPixels=" + positionOffsetPixels);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) line.getLayoutParams();
                layoutParams.leftMargin = (int) (positionOffset * width) + position * width;
                line.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
//                LogUtil.e(TAG, "onPageSelected  position=" + position + "     lastSelectedIndex=" + lastSelectedIndex);
                tabItemList.get(lastSelectedIndex).setSelected(false);
                tabItemList.get(position).setSelected(true);
                lastSelectedIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE: // 0
//                        LogUtil.e(TAG, "onPageScrollStateChanged  state=SCROLL_STATE_IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING: // 1
//                        LogUtil.e(TAG, "onPageScrollStateChanged  state=SCROLL_STATE_DRAGGING");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING: // 2
//                        LogUtil.e(TAG, "onPageScrollStateChanged  state=SCROLL_STATE_SETTLING");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    private void addFragments(String title) {
        TabFragment tabFragment = TabFragment.newInstance(title);

        fragmentList.add(tabFragment);
    }


    @Override
    public void onClick(View v) {

    }
}
