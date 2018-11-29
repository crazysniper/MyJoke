package application.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.model.Student;

/**
 * Created by Gao on 2018/11/29.
 */

public class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.LinearLayoutViewHolder> {

    private List<Student> stuList = new ArrayList<>();
    private LayoutInflater layoutInflater = null;
    private LinearLayoutClickListener listener;
    private LinearLayoutLongClickListener longListener;


    public LinearLayoutAdapter(Context context, List<Student> stuList) {
        this.stuList = stuList;
        layoutInflater = LayoutInflater.from(context);
    }

    static class LinearLayoutViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public LinearLayoutViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    @Override
    public LinearLayoutAdapter.LinearLayoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        LinearLayoutViewHolder holder = new LinearLayoutViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final LinearLayoutViewHolder holder, final int position) {
        holder.name.setText(stuList.get(position).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(holder.itemView, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longListener != null) {
                    longListener.onLongClick(holder.itemView, position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
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
