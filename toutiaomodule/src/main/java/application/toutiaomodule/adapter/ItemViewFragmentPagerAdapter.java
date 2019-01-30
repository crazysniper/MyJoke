package application.toutiaomodule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import application.toutiaomodule.bean.TouTiaoFragmentItem;
import application.toutiaomodule.ui.fragment.ToutiaoItemFragment;

/**
 * Created by Gao on 2019/1/25.
 */

public class ItemViewFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<ToutiaoItemFragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;

    public ItemViewFragmentPagerAdapter(FragmentManager fm, List<ToutiaoItemFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        TouTiaoFragmentItem itemBean = fragmentList.get(position).getArguments().getParcelable("name");
        return itemBean == null ? null : itemBean.getName();
    }
}
