package application.eventdemo.ui;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.myjoke.baselibray.base.BaseActivity;

import application.eventdemo.R;
import application.eventdemo.util.EventUtil;
import application.eventdemo.widget.LayoutX;
import application.eventdemo.widget.TextViewX;

public class EventInfoActivity extends BaseActivity {

    private LayoutX layout;
    private TextViewX view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_info;
    }

    @Override
    public void initView() {
        layout = (LayoutX) findViewById(R.id.layout);
        view = (TextViewX) findViewById(R.id.view);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUtil.showEvent(null, "ViewGroup   onClick");
            }
        });

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EventUtil.showEvent(event, "ViewGroup   onTouch");
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUtil.showEvent(null, "View   onClick");
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EventUtil.showEvent(event, "View   onTouch");
                return false;
            }
        });

    }

    @Override
    public void initData() {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventUtil.showEvent(ev, "Activity   dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventUtil.showEvent(event, "Activity   onTouchEvent");
        return super.onTouchEvent(event);
    }

    GestureDetector.OnGestureListener gestureDetector = new GestureDetector.OnGestureListener() {

        //  用户按下屏幕就会触发
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        // 如果是按下的时间超过瞬间，而且在按下的时候没有松开或者是拖动的，那么onShowPress就会执行，具体这个瞬间是多久，我也不清楚呃……
        @Override
        public void onShowPress(MotionEvent e) {

        }

        // 从名字也可以看出,一次单独的轻击抬起操作,也就是轻击一下屏幕，立刻抬起来，才会有这个触发，
        // 当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以也就不会触发这个事件

        /**
         *  触发顺序：
         *     点击一下非常快的（不滑动）Touchup：
         *     onDown->onSingleTapUp->onSingleTapConfirmed
         *     点击一下稍微慢点的（不滑动）Touchup：
         *     onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        /**
         * 在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法在ACTION_MOVE动作发生时就会触发
         *
         *    滑屏：手指触动屏幕后，稍微滑动后立即松开
         *     onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling
         *     拖动
         *     onDown------》onScroll----》onScroll------》onFiling
         *
         *     可见，无论是滑屏，还是拖动，影响的只是中间OnScroll触发的数量多少而已，最终都会触发onFling事件
         *
         * @param e1
         * @param e2
         * @param distanceX
         * @param distanceY
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        // 长按触摸屏，超过一定时长，就会触发这个事件
        //  触发顺序：onDown->onShowPress->onLongPress
        @Override
        public void onLongPress(MotionEvent e) {

        }

        /**
         * 滑屏，用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
         * @param e1 第1个ACTION_DOWN MotionEvent
         * @param e2 最后一个ACTION_MOVE MotionEvent
         * @param velocityX  X轴上的移动速度，像素/秒
         * @param velocityY  Y轴上的移动速度，像素/秒
         * @return
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };


    GestureDetector.OnDoubleTapListener onDoubleTapListener = new GestureDetector.OnDoubleTapListener() {
        /**
         * 单击事件。
         * 用来判定该次点击是SingleTap而不是DoubleTap，如果连续点击两次就是DoubleTap手势，
         * 如果只点击一次，系统等待一段时间后没有收到第二次点击则判定该次点击为SingleTap而不是DoubleTap，然后触发SingleTapConfirmed事件。
         *
         * 触发顺序是：OnDown->OnsingleTapUp->OnsingleTapConfirmed
         *
         *
         * 关于onSingleTapConfirmed和onSingleTapUp的一点区别：
         * OnGestureListener有这样的一个方法onSingleTapUp，和onSingleTapConfirmed容易混淆。
         * 二者的区别是：
         * onSingleTapUp，只要手抬起就会执行，
         * onSingleTapConfirmed来说，如果双击的话，则onSingleTapConfirmed不会执行。
         *
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        /**
         * 双击事件
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        /**
         * 双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件；
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    };

}
