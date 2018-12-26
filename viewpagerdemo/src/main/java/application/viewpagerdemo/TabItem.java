package application.viewpagerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myjoke.baselibray.util.LogUtil;

/**
 * Created by Gao on 2018/12/26.
 */

public class TabItem {

    private Context context;
    private int imageResId;
    private int selectedImgResId;
    private String title;
    private View view;
    private boolean selected;
    private ImageView imageView;
    private TextView titleView;
    private ViewGroup.LayoutParams layoutParams;

    public TabItem(Context context, int imageResId, int selectedImgResId, String title) {
        this.context = context;
        this.imageResId = imageResId;
        this.selectedImgResId = selectedImgResId;
        this.title = title;
    }

    public TabItem(Context context, int imageResId, int selectedImgResId, String title, ViewGroup.LayoutParams layoutParams) {
        this.context = context;
        this.imageResId = imageResId;
        this.selectedImgResId = selectedImgResId;
        this.title = title;
        this.layoutParams = layoutParams;
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
        if (view != null) {
            view.setLayoutParams(layoutParams);
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            if (imageView != null) {
                imageView.setImageResource(selectedImgResId);
            }
        } else {
            if (imageView != null) {
                imageView.setImageResource(imageResId);
            }
        }
    }

    public boolean selected() {
        return selected;
    }

    public View getView() {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
            imageView = (ImageView) view.findViewById(R.id.icon);
            titleView = (TextView) view.findViewById(R.id.title);

            imageView.setImageResource(imageResId);
            titleView.setText(title);

            if (layoutParams != null) {
                view.setLayoutParams(layoutParams);
            }
        }
        return view;
    }
}
