package application.viewpagerdemo.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.myjoke.baselibray.base.BaseLazyFragment;
import com.myjoke.baselibray.util.LogUtil;

import application.viewpagerdemo.R;

/**
 * Created by Gao on 2018/12/26.
 */

public class TabFragment extends BaseLazyFragment {

    private String title;

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item;
    }

    @Override
    protected void initView() {
        LogUtil.e("title=" + title);
        TextView titleView = view.findViewById(R.id.textView);
        titleView.setText(title);
    }

    @Override
    protected void initData() {

    }
}
