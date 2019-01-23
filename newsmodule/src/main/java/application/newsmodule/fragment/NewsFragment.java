package application.newsmodule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.myjoke.baselibray.base.BaseFragment;

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

    private String names[] = {"关注", "推荐", "直播", "影视", "游戏",
            "社会", "NBA", "音乐", "综艺", "搞笑", "时尚"};


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
        NewsFragmentItem item = null;
        NewItemFragment fragment = null;
        for (String name : names) {
            item = new NewsFragmentItem(name);

            fragment = new NewItemFragment();


            Bundle bundle = new Bundle();
//            bundle.putString("name", name);
            bundle.putParcelable("name", item);

            fragment.setArguments(bundle);
            fragmentList.add(fragment);
            fragment = null;
            tablayout.addTab(tablayout.newTab().setText(name));
        }
        adapter.notifyDataSetChanged();
    }

}
