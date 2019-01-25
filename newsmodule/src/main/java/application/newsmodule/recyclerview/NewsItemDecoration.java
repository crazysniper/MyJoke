package application.newsmodule.recyclerview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ScreenUtil;

import application.newsmodule.R;

/**
 * Created by Gao on 2019/1/25.
 */

public class NewsItemDecoration extends RecyclerView.ItemDecoration {

    private ColorDrawable colorDrawable = null;
    private int paddingLeft, paddingRight, paddingTop;
    private int lineHeight = 2;

    public NewsItemDecoration(Context context) {
        super();
        colorDrawable = new ColorDrawable(Color.parseColor("#d8d8d8"));
        paddingLeft = ScreenUtil.getDimensionPixelSize(context, R.dimen.news_item_padding_left);
        paddingRight = ScreenUtil.getDimensionPixelSize(context, R.dimen.news_item_padding_right);
        paddingTop = ScreenUtil.getDimensionPixelSize(context, R.dimen.news_item_padding_top);
    }

    /*设置间隔的大小的，修改ourRect这个参数即可，里边有left，right，top，bottom属性
    举例,如下，第一个item，有个top 30，那么你看到的效果就是第一个item距离上边有30个间隔，就是这里设置的。

     val i=parent.getChildAdapterPosition(view)
    if(i==0)
            outRect.top=30

    */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        LogUtil.e("position=" + position + "    paddingLeft=" + paddingLeft + "    paddingRight=" + paddingRight + "   lineHeight=" + lineHeight);
        if (position == 0) {
            outRect.set(paddingLeft, 0, paddingRight, 0);
        } else {
            outRect.set(paddingLeft, lineHeight, paddingRight, 0);
        }

    }

    // 来操作上边弄的间隔的，默认间隔就是个空白，这里你可以随便画点啥
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        c.drawColor(Color.RED);
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();

        int childCount = parent.getChildCount();
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                int left = child.getLeft();
                int top = child.getBottom();
                int right = child.getRight();
                int bottom = top + lineHeight;
                LogUtil.e("position=" + i + "    left=" + left + "    top=" + top + "   right=" + right + "   bottom=" + bottom);
                colorDrawable.setBounds(left, top, right, bottom);
                colorDrawable.draw(c);
            }
        }
    }

    // 这个看名字，就是画在最顶层的，你也可以理解成最后画
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


    /*


onDraw(),可以实现类似绘制背景的效果，内容在上面。（如绘制item的分割线）
onDrawOver()，可以绘制在内容的上面，覆盖内容。（如给item添加覆盖在上面的角标等）。
    这个方法是相对与整个视图的，不受Item的限制，比如我们可以在视图的顶部加一个悬浮的层等等。
getItemOffsets(),可以实现类似padding的效果

一般绘制分割线需onDraw()和getItemOffsets()；而仅仅是添加间距的话只用getItemOffsets()就可以实现。

     */
}
