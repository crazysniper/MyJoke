package application.newsmodule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myjoke.baselibray.base.BaseLazyFragment;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.newsmodule.R;
import application.newsmodule.R2;
import application.newsmodule.adapter.NewsAdapter;
import application.newsmodule.bean.News;
import application.newsmodule.bean.NewsFragmentItem;
import application.newsmodule.recyclerview.NewsItemAnimator;
import application.newsmodule.recyclerview.NewsItemDecoration;
import application.newsmodule.util.Constant;
import application.okhttpdemo.bean.ResultBean;
import application.okhttpdemo.callback.ResultCallBack;
import application.okhttpdemo.util.HttpUtil;
import application.webviewdemo.util.WebViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gao on 2019/1/22.
 */

public class NewItemFragment extends BaseLazyFragment implements NewsAdapter.OnItemClickListener {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private NewsFragmentItem newsFragmentItem;
    private NewsAdapter adapter;

    public List<News.ResultBean.DataBean> dataList = new ArrayList<>();
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
        ARouter.getInstance().inject(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new NewsItemDecoration(activity));
        recyclerView.setItemAnimator(new NewsItemAnimator());

        adapter = new NewsAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        LogUtil.e("NewItemFragment  initData    name=" + name);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + Constant.APP_CODE);

        Map<String, String> queries = new HashMap<>();
        queries.put("type", newsFragmentItem.getType());


        HttpUtil.getInstance().doGet(Constant.HOST, null, headers, queries, new ResultCallBack() {
            @Override
            public void error(IOException e) {
                LogUtil.e("error==" + e.getMessage());
            }

            @Override
            public void success(ResultBean bean) {
                LogUtil.e("success=" + bean.getCode() + " = " + bean.getBody());

                News news = new Gson().fromJson(bean.getBody(), new TypeToken<News>() {
                }.getType());

                if (!"0".equals(news.getError_code())) {
                    News.ResultBean resultBean = news.getResult();
                    if ("1".equals(resultBean.getStat())) {
                        List<News.ResultBean.DataBean> list = resultBean.getData();
//                        dataList.clear();
//                        dataList.addAll(list);

                        adapter.addNews(list);

                        LogUtil.e("size==" + list.size());
//                        for (News.ResultBean.DataBean dataBean : list) {
//                            LogUtil.e("title=" + dataBean.getTitle());
//                            LogUtil.e("date=" + dataBean.getDate());
//                            LogUtil.e("author_name=" + dataBean.getAuthor_name());
//                        }
//                        adapter.notifyDataSetChanged();
                    }
                } else {
                    ToastUtil.getInstance().showToast(news.getReason());
                }
            }
        });
    }


    @Override
    public void onItemLClick(int position) {
        ARouter.getInstance().build(WebViewConstant.NewsDetailActivity)
                .withString("url", dataList.get(position).getUrl())
                .navigation();
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
