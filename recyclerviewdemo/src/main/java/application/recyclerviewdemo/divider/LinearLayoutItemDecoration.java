package application.recyclerviewdemo.divider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gao on 2018/11/30.
 */

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration {

    public LinearLayoutItemDecoration() {
    }

    // getItemOffsets 是针对每一个 ItemView，而 onDraw 方法却是针对 RecyclerView 本身，
    // 所以在 onDraw 方法中需要遍历屏幕上可见的 ItemView，分别获取它们的位置信息，然后分别的绘制对应的分割线。

    // 可以实现类似padding的效果
    // 指定 outRect 中的 top、left、right、bottom 就可以控制各个方向的间隔了。注意的是这些属性都是偏移量，是指偏移 ItemView 各个方向的数值
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 10;
        outRect.top = 20;
        outRect.right = 4;
        outRect.bottom = 8;
    }

    // 实现类似绘制背景的效果，内容在上面。
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.drawColor(Color.WHITE);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            if (index == 0) {
                continue;
            }


        }
    }

    // 可以绘制在内容的上面，覆盖内容。
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
