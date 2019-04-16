package application.viewpagerdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.myjoke.baselibray.base.BaseLazyFragment;
import com.myjoke.baselibray.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import application.viewpagerdemo.R;
import application.viewpagerdemo.R2;
import application.viewpagerdemo.adapter.TabItemListAdapter;
import application.viewpagerdemo.ui.ViewPager_Fragment_Activity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gao on 2018/12/26.
 */

public class TabFragment extends BaseLazyFragment {

    private String title;
    @BindView(R2.id.listView)
    ListView listView;

    @BindView(R2.id.textView)
    TextView titleView;

    private ToActivityListener listener;

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ToActivityListener) context;
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
        ButterKnife.bind(this, view);
        LogUtil.e("title=" + title);
        titleView.setText(title);
    }

    @Override
    protected void initData() {
        List<String> dataList = new ArrayList<>();
        for (int index = 0; index < 30; index++) {
            dataList.add("item  " + index);
        }
        TabItemListAdapter adapter = new TabItemListAdapter(dataList);
        listView.setAdapter(adapter);
    }

    @OnClick({R2.id.textView})
    public void onClick(View view) {
        if (view.getId() == R.id.textView) {
            if (listener != null) {
                listener.toActivity("从Fragment中传值给Activity");
            }
            ViewPager_Fragment_Activity activity = (ViewPager_Fragment_Activity) getActivity();
            TabFragment tabItem = (TabFragment) activity.getFragment(1);
            tabItem.setTitle(tabItem.getTitle() + "->接收从第一个Fragment传值过来的了。");
        }
    }


    public void setTitle(String title) {
        this.title = title;
        titleView.setText(title);
    }

    public String getTitle() {
        return titleView.getText().toString();
    }

    public interface ToActivityListener {
        public void toActivity(String text);
    }
}
