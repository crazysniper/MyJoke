package com.myjoke.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import com.myjoke.R;
import com.myjoke.baselibray.base.BaseFragment;


public class NewsFragment extends BaseFragment {


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

    }
}
