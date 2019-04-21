package application.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myjoke.baselibray.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.model.Student;

/**
 * Created by Gao on 2018/11/29.
 */

public class LinearLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_FOOTER = 2;


    private List<Student> stuList = new ArrayList<>();
    private LayoutInflater layoutInflater = null;
    private LinearLayoutClickListener listener;
    private LinearLayoutLongClickListener longListener;

    public LinearLayoutAdapter(Context context, List<Student> stuList) {
        this.stuList = stuList;
        layoutInflater = LayoutInflater.from(context);

        LogUtil.e("LinearLayoutAdapter", "LinearLayoutAdapter构造方法");
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    static class LinearLayoutViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private CardView cardView;


        public LinearLayoutViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.e("LinearLayoutAdapter", "LinearLayoutViewHolder");
        View view = null;
        switch (viewType) {
            case ITEM_TYPE_HEADER:
                view = layoutInflater.inflate(R.layout.item_layout, parent, false);
                return new HeaderViewHolder(view);

            case ITEM_TYPE_CONTENT:
                view = layoutInflater.inflate(R.layout.item_layout, parent, false);
                return new LinearLayoutViewHolder(view);
            case ITEM_TYPE_FOOTER:
                view = layoutInflater.inflate(R.layout.item_layout, parent, false);
                return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        LogUtil.e("LinearLayoutAdapter", "onBindViewHolder  position=" + position + "     name=" + stuList.get(position));

        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof LinearLayoutViewHolder) {

            ((LinearLayoutViewHolder) holder).name.setText(stuList.get(position).getName());
            ((LinearLayoutViewHolder) holder).name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        LogUtil.e("点击下标=" + position + ",getItemCount==" + getItemCount());
                        LogUtil.e("getPosition=" + holder.getPosition() + ",getLayoutPosition==" + holder.getLayoutPosition());
                        LogUtil.e("getAdapterPosition=" + holder.getAdapterPosition() + ",getOldPosition==" + holder.getOldPosition());
                        LogUtil.e("getItemId=" + holder.getItemId() + ",getItemViewType==" + holder.getItemViewType() + ",,,isRecyclable==" + holder.isRecyclable());
                        listener.onClick(((LinearLayoutViewHolder) holder).itemView, position);
                    }
                }
            });

            ((LinearLayoutViewHolder) holder).cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longListener != null) {
                        longListener.onLongClick(((LinearLayoutViewHolder) holder).cardView, position);
                    }
                    return false;
                }
            });
        } else if (holder instanceof FooterViewHolder) {

        }

    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return ITEM_TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
//        LogUtil.e("LinearLayoutAdapter", "getItemCount  getItemCount=" + stuList.size());
        return stuList.size();
    }

    public void setLinearLayoutClickListener(LinearLayoutClickListener listener) {
        this.listener = listener;
    }

    public void setLinearLayoutLongClickListener(LinearLayoutLongClickListener longListener) {
        this.longListener = longListener;
    }

    public interface LinearLayoutClickListener {
        void onClick(View view, int position);
    }

    public interface LinearLayoutLongClickListener {
        void onLongClick(View view, int position);
    }
}
