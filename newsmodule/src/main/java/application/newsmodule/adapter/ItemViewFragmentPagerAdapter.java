package application.newsmodule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import application.newsmodule.fragment.NewItemFragment;

/**
 * Created by Gao on 2018/12/26.
 */

public class ItemViewFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<NewItemFragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;

    public ItemViewFragmentPagerAdapter(FragmentManager fm, List<NewItemFragment> fragmentList) {
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
}
