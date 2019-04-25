package com.myjoke.baselibray.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myjoke.baselibray.util.LogUtil;

/**
 * Created by Gao on 2018/12/12.
 */

public abstract class BaseLazyFragment extends Fragment {
    protected Activity activity;

    protected View view;


    /**
     * Fragment当前状态是否可见
     */
    private boolean isVisible = false;

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;

    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            isPrepared = true;
            view = inflater.inflate(getLayoutId(), container, false);
            LogUtil.e("onCreateView   isPrepared=" + isPrepared + "       isVisible=" + isVisible + "     mHasLoadedOnce=" + mHasLoadedOnce);
            init();
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.e("setUserVisibleHint   isVisibleToUser=" + isVisibleToUser + "  isPrepared=" + isPrepared + "       isVisible=" + isVisible + "     mHasLoadedOnce=" + mHasLoadedOnce);
        if (isVisibleToUser) {
            isVisible = true;
            init();
        } else {
            isVisible = false;
        }
    }

    public boolean Visible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    private void init() {
        LogUtil.e("init   isPrepared=" + isPrepared + "       isVisible=" + isVisible + "     mHasLoadedOnce=" + mHasLoadedOnce);
        if (!isPrepared || !Visible() || mHasLoadedOnce) {
            return;
        }
        activity = getActivity();
        initView();
        initData();
        mHasLoadedOnce = true;
    }


    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
