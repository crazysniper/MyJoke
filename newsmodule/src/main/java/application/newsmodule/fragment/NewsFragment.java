package application.newsmodule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myjoke.baselibray.base.BaseFragment;
import com.myjoke.baselibray.util.ScreenUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import application.newsmodule.R;
import application.newsmodule.R2;
import application.newsmodule.adapter.ItemViewFragmentPagerAdapter;
import application.newsmodule.bean.NewsFragmentItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gao on 2019/1/22.
 */


public class NewsFragment extends BaseFragment {

    @BindView(R2.id.tablayout)
    TabLayout tablayout;

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    private ItemViewFragmentPagerAdapter adapter;
    private List<NewItemFragment> fragmentList = new ArrayList<>();

    public NewsFragment() {
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, view);

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        adapter = new ItemViewFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);

        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        List<NewsFragmentItem> itemList = initTabLayout();

        NewItemFragment fragment = null;
        for (NewsFragmentItem item : itemList) {
            fragment = new NewItemFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable("name", item);
            fragment.setArguments(bundle);

            fragmentList.add(fragment);
            fragment = null;

            tablayout.addTab(tablayout.newTab().setText(item.getName()));
        }
        adapter.notifyDataSetChanged();

//        reflex(tablayout);
    }

    private List<NewsFragmentItem> initTabLayout() {
        List<NewsFragmentItem> itemList = new ArrayList<>();
        itemList.add(new NewsFragmentItem("头条", "top"));
        itemList.add(new NewsFragmentItem("社会", "shehui"));
        itemList.add(new NewsFragmentItem("国内", "guonei"));
        itemList.add(new NewsFragmentItem("国际", "guoji"));
        itemList.add(new NewsFragmentItem("娱乐", "yule"));
        itemList.add(new NewsFragmentItem("体育", "tiyu"));
        itemList.add(new NewsFragmentItem("军事", "junshi"));
        itemList.add(new NewsFragmentItem("科技", "keji"));
        itemList.add(new NewsFragmentItem("财经", "caijing"));
        itemList.add(new NewsFragmentItem("时尚", "shishang"));
        return itemList;
    }


    /**
     * 设置 导航栏下短横线与左右两边间距的
     * https://www.jianshu.com/p/6f6303ff2b7c
     * @param tabLayout
     */
    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    int dp10 = ScreenUtil.dip2px(tabLayout.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
