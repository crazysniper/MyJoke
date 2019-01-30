package application.toutiaomodule.ui.fragment;

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

import application.toutiaomodule.R;
import application.toutiaomodule.R2;
import application.toutiaomodule.adapter.ItemViewFragmentPagerAdapter;
import application.toutiaomodule.bean.TouTiaoFragmentItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gao on 2019/1/25.
 */

public class TouTiaoFragment extends BaseFragment {

    @BindView(R2.id.tablayout)
    TabLayout tablayout;

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    private ItemViewFragmentPagerAdapter adapter;
    private List<ToutiaoItemFragment> fragmentList = new ArrayList<>();

    public TouTiaoFragment() {
    }

    public static TouTiaoFragment newInstance(String param1, String param2) {
        TouTiaoFragment fragment = new TouTiaoFragment();
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
        return R.layout.fragment_toutiao;
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
        List<TouTiaoFragmentItem> itemList = initTabLayout();

        ToutiaoItemFragment fragment = null;
        for (TouTiaoFragmentItem item : itemList) {
            fragment = new ToutiaoItemFragment();

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

    private List<TouTiaoFragmentItem> initTabLayout() {
        List<TouTiaoFragmentItem> itemList = new ArrayList<>();
        itemList.add(new TouTiaoFragmentItem("热点", "news_hot"));
        itemList.add(new TouTiaoFragmentItem("视频", "video"));
        itemList.add(new TouTiaoFragmentItem("社会", "news_society"));
        itemList.add(new TouTiaoFragmentItem("娱乐", "news_entertainment"));
        itemList.add(new TouTiaoFragmentItem("问答", "question_and_answer"));
        itemList.add(new TouTiaoFragmentItem("图片", "组图"));
        itemList.add(new TouTiaoFragmentItem("科技", "news_tech"));
        itemList.add(new TouTiaoFragmentItem("汽车", "news_car"));
        itemList.add(new TouTiaoFragmentItem("体育", "news_sport"));
        itemList.add(new TouTiaoFragmentItem("财经", "news_finance"));
        itemList.add(new TouTiaoFragmentItem("军事", "news_military"));
        itemList.add(new TouTiaoFragmentItem("国际", "news_world"));
        itemList.add(new TouTiaoFragmentItem("段子", "essay_joke"));
        itemList.add(new TouTiaoFragmentItem("趣图", "image_funny"));
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
