package application.newsmodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myjoke.baselibray.util.TimeUtil;

import java.text.ParseException;
import java.util.List;

import application.newsmodule.R;
import application.newsmodule.bean.News;

/**
 * Created by Gao on 2019/1/25.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    public List<News.ResultBean.DataBean> dataList;
    private OnItemClickListener listener;

    public NewsAdapter(List<News.ResultBean.DataBean> dataList) {
        this.dataList = dataList;
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {
        //        @BindView(R2.id.title)
        private TextView tvTitle;
        //        @BindView(R2.id.author)
        private TextView tvAuthor;
        //        @BindView(R2.id.date)
        private TextView tvDate;

        public NewsHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvAuthor = (TextView) itemView.findViewById(R.id.author);
            tvDate = (TextView) itemView.findViewById(R.id.date);

        }
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        NewsHolder holder = new NewsHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, final int position) {
        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvAuthor.setText(dataList.get(position).getAuthor_name());
        try {
            holder.tvDate.setText(TimeUtil.setTimeDesc(dataList.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemLClick(position);
                }
            }
        });
    }

    public void addNews(List<News.ResultBean.DataBean> dataList) {
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        public void onItemLClick(int position);
    }


}
