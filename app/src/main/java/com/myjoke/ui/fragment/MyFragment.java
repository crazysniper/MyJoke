package com.myjoke.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import com.myjoke.R;
import com.myjoke.baselibray.base.BaseFragment;


public class MyFragment extends BaseFragment {


    public MyFragment() {
    }


    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
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
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }
}
