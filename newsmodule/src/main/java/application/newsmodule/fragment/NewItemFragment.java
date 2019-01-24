package application.newsmodule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myjoke.baselibray.base.BaseLazyFragment;
import com.myjoke.baselibray.util.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import application.newsmodule.R;
import application.newsmodule.R2;
import application.newsmodule.bean.NewsFragmentItem;
import application.newsmodule.util.Constant;
import application.okhttpdemo.bean.ResultBean;
import application.okhttpdemo.callback.ResultCallBack;
import application.okhttpdemo.util.HttpUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gao on 2019/1/22.
 */

public class NewItemFragment extends BaseLazyFragment {
    @BindView(R2.id.tv)
    TextView tv;
    private NewsFragmentItem newsFragmentItem;
    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            newsFragmentItem = bundle.getParcelable("name");
            name = newsFragmentItem == null ? "" : newsFragmentItem.getName();
        }
        LogUtil.e("NewItemFragment  onCreate    name=" + name);
    }

    @Override
    protected int getLayoutId() {
        LogUtil.e("NewItemFragment  getLayoutId    name=" + name);
        return R.layout.item_fragment_news;
    }

    @Override
    protected void initView() {
        LogUtil.e("NewItemFragment  initView    name=" + name);
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        LogUtil.e("NewItemFragment  initData    name=" + name);
        tv.setText(name);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + Constant.APP_CODE);

        Map<String, String> queries = new HashMap<>();
        headers.put("type", newsFragmentItem.getType());



        HttpUtil.getInstance().doGet(Constant.HOST, null, headers, queries, new ResultCallBack() {
            @Override
            public void error(IOException e) {
                LogUtil.e("error==" + e.getMessage());
            }

            @Override
            public void success(ResultBean bean) {
                LogUtil.e("success=" + bean.getCode() + " = " + bean.getBody());
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e("NewItemFragment  onCreateView    name=" + name);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.e("NewItemFragment  onAttach    name=" + name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.e("NewItemFragment  onActivityCreated    name=" + name);
    }

    @Override
    public void onStart() {
        LogUtil.e("NewItemFragment  onStart    name=" + name);
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtil.e("NewItemFragment  onResume    name=" + name);
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtil.e("NewItemFragment  onPause    name=" + name);
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtil.e("NewItemFragment  onStop    name=" + name);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        LogUtil.e("NewItemFragment  onDestroy    name=" + name);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        LogUtil.e("NewItemFragment  onDestroyView    name=" + name);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtil.e("NewItemFragment  onDetach    name=" + name);
        super.onDetach();
    }
}
