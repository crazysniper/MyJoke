package application.recyclerviewdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import application.recyclerviewdemo.R;
import application.recyclerviewdemo.model.Student;
import application.recyclerviewdemo.viewholder.ViewHolder;

public class ListViewAdapter extends CommonAdapter<Student> {

    public ListViewAdapter(Context context, List<Student> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, Student item) {
        ((TextView) helper.getView(R.id.name)).setText(item.getName());
    }
}
