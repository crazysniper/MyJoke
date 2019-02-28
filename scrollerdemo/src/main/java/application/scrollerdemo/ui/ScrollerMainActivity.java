package application.scrollerdemo.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;

import application.scrollerdemo.R;
import application.scrollerdemo.R2;
import application.scrollerdemo.util.ScrollerConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ScrollerConstant.ScrollerMainActivity)
public class ScrollerMainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R2.id.scrollerTo)
    Button scrollerTo;
    @BindView(R2.id.scrollerBy)
    Button scrollerBy;
    @BindView(R2.id.scrollerToZheng)
    Button scrollerToZheng;
    @BindView(R2.id.scrollerByZheng)
    Button scrollerByZheng;
    @BindView(R2.id.getInfo)
    Button getInfo;


    @BindView(R2.id.getLeft)
    Button getLeft;
    @BindView(R2.id.getX)
    Button getX;
    @BindView(R2.id.getX2)
    Button getX2;
    @BindView(R2.id.anim)
    Button anim;
    @BindView(R2.id.scroll)
    TextView scroll;
    @BindView(R2.id.textView)
    TextView textView;
    @BindView(R2.id.left)
    TextView left;
    @BindView(R2.id.x)
    TextView x;
    @BindView(R2.id.translationX)
    TextView translationX;
    @BindView(R2.id.parent)
    LinearLayout parent;

    private ObjectAnimator objectAnimator = null;

    private int rightBottomScrollerTo, rightBottomScrollerBy, leftTopScrollerTo, leftTopScrollerBy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroller_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        rightBottomScrollerTo = getResources().getDimensionPixelSize(R.dimen.scrollTofu);
        rightBottomScrollerBy = getResources().getDimensionPixelSize(R.dimen.scrollByfu);

        leftTopScrollerTo = getResources().getDimensionPixelOffset(R.dimen.scrollTozheng);
        leftTopScrollerBy = getResources().getDimensionPixelOffset(R.dimen.scrollByzheng);

        textView.post(new Runnable() {
            @Override
            public void run() {
                LogUtil.e("Activity post width=" + textView.getWidth() + "   height=" + textView.getHeight());
            }
        });

        final ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (observer.isAlive()) {
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    LogUtil.e("Activity getViewTreeObserver width=" + textView.getWidth() + "   height=" + textView.getHeight());
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.scrollerTo, R2.id.scrollerBy, R2.id.scrollerToZheng, R2.id.scrollerByZheng,
            R2.id.getInfo, R2.id.textView, R2.id.getLeft, R2.id.getX, R2.id.getX2, R2.id.anim,
            R2.id.offsetLeftAndRight1, R2.id.offsetLeftAndRight2, R2.id.offsetTopAndBottom1, R2.id.offsetTopAndBottom2,
            R2.id.setTranslationX1, R2.id.setTranslationX2, R2.id.setTranslationY1, R2.id.setTranslationY2})
    public void onClick(View v) {
        if (v.getId() == R.id.scrollerTo) { // 向右下角scrollerTo
            parent.scrollTo(-rightBottomScrollerTo, -rightBottomScrollerTo);
        } else if (v.getId() == R.id.scrollerBy) { // 向右下角scrollerBy
            parent.scrollBy(-rightBottomScrollerBy, -rightBottomScrollerBy);
        } else if (v.getId() == R.id.scrollerToZheng) { // 向左上角scrollerTo
            parent.scrollTo(leftTopScrollerTo, leftTopScrollerTo);
        } else if (v.getId() == R.id.scrollerByZheng) { // 向左上角scrollerBy
            parent.scrollBy(leftTopScrollerBy, leftTopScrollerBy);
        } else if (v.getId() == R.id.getInfo) { // 获取关于scoller的坐标
            int scrollX = textView.getScrollX();
            int scrollY = textView.getScrollY();
            String aa = String.format("getScrollX=%d,getScrollY=%d", scrollX, scrollY);
            scroll.setText(aa);
            LogUtil.e("获取关于scoller的坐标 =" + aa);
        } else if (v.getId() == R.id.textView) { // 点击移动的View
            ToastUtil.getInstance().showToast(ScrollerMainActivity.this, "点击到了");

            String aa = String.format("getLeft=%d,getTop=%d,getRight=%d,getBottom=%d",
                    textView.getLeft(), textView.getTop(), textView.getRight(), textView.getBottom());

            String bb = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                bb = String.format("getX=%f,getY=%f,getZ=%f", textView.getX(), textView.getY(), textView.getZ());
            } else {
                bb = String.format("getX=%f,getY=%f", textView.getX(), textView.getY());
            }

            String cc = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                cc = String.format("getTranslationX=%f,getTranslationY=%f,getTranslationZ=%f",
                        textView.getTranslationX(), textView.getTranslationY(), textView.getTranslationZ());
            } else {
                cc = String.format("getTranslationX=%s,getTranslationY=%s",
                        textView.getTranslationX(), textView.getTranslationY());
            }

            LogUtil.e("点击移动的View =" + aa);
            LogUtil.e("点击移动的View =" + bb);
            LogUtil.e("点击移动的View =" + cc);

        } else if (v.getId() == R.id.getLeft) { // 获取关于Left、Right、Top、Bottom的坐标
            String aa = String.format("getLeft=%d,getTop=%d,getRight=%d,getBottom=%d",
                    textView.getLeft(), textView.getTop(), textView.getRight(), textView.getBottom());
            left.setText(aa);
            LogUtil.e("获取关于Left、Right、Top、Bottom的坐标 =" + aa);
        } else if (v.getId() == R.id.getX) { // 获取关于x,y的坐标
            String aa = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                aa = String.format("getX=%f,getY=%f,getZ=%f", textView.getX(), textView.getY(), textView.getZ());
            } else {
                aa = String.format("getX=%f,getY=%f", textView.getX(), textView.getY());
            }
            x.setText(aa);
            LogUtil.e("获取关于x,y的坐标 =" + aa);
        } else if (v.getId() == R.id.getX2) { // 获取关于translationX,translationY的坐标
            String aa = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                aa = String.format("getTranslationX=%f,getTranslationY=%f,getTranslationZ=%f",
                        textView.getTranslationX(), textView.getTranslationY(), textView.getTranslationZ());
            } else {
                aa = String.format("getTranslationX=%s,getTranslationY=%s", textView.getTranslationX(), textView.getTranslationY());
            }
            translationX.setText(aa);
            LogUtil.e("获取关于translationX,translationY的坐标 =" + aa);
        } else if (v.getId() == R.id.anim) { // 开启动画
            if (objectAnimator == null) {
//                objectAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0, 1);
                objectAnimator = ObjectAnimator.ofFloat(textView, "translationX", 0.0f, 100.0f, 200.0f, 300.0f);

                objectAnimator.setDuration(2000);
                objectAnimator.setRepeatCount(0);
                objectAnimator.setRepeatMode(ValueAnimator.RESTART);
            }

            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
//                    LogUtil.e("update   animatedValue=" + animation.getAnimatedValue()
//                            + "    fraction=" + animation.getAnimatedFraction());
                }
            });

            objectAnimator.start();
            ToastUtil.getInstance().showToast(ScrollerMainActivity.this, "开启动画");
        } else if (v.getId() == R.id.offsetLeftAndRight1) { // 向左挪动。offsetLeftAndRight改变mLeft和mRight的值，offset为正View整体位置向右偏移，为负则向左偏移
            textView.offsetLeftAndRight(-50);
            LogUtil.e("offsetLeftAndRight为负，向左挪动");
        } else if (v.getId() == R.id.offsetLeftAndRight2) { // 向右挪动。offsetLeftAndRight改变mLeft和mRight的值，offset为正View整体位置向右偏移，为负则向左偏移
            textView.offsetLeftAndRight(50);
            LogUtil.e("offsetLeftAndRight为正，向右挪动");
        } else if (v.getId() == R.id.offsetTopAndBottom1) { // 向上挪动。offsetTopAndBottom改变mTop和mBottom的值，offset为正View整体位置向下偏移，为负则向上偏移。
            textView.offsetTopAndBottom(-50);
            LogUtil.e("offsetTopAndBottom为负，向上挪动");
        } else if (v.getId() == R.id.offsetTopAndBottom2) { // 向下挪动。offsetTopAndBottom改变mTop和mBottom的值，offset为正View整体位置向下偏移，为负则向上偏移。
            textView.offsetTopAndBottom(50);
            LogUtil.e("offsetTopAndBottom为正，向下挪动");
        } else if (v.getId() == R.id.setTranslationX1) { // 向左挪动。setTranslationX为正View整体位置向右偏移，为负则向左偏移
            textView.setTranslationX(-50);
            LogUtil.e("setTranslationX为负向左挪动");
        } else if (v.getId() == R.id.setTranslationX2) { // 向右挪动。setTranslationX为正View整体位置向右偏移，为负则向左偏移
            textView.setTranslationX(50);
            LogUtil.e("setTranslationX为正，向右挪动");
        } else if (v.getId() == R.id.setTranslationY1) { // 向上挪动。setTranslationY为正View整体位置向下偏移，为负则向上偏移。
            textView.setTranslationY(-50);
            LogUtil.e("setTranslationY为负，向上挪动");
        } else if (v.getId() == R.id.setTranslationY2) { // 向下挪动。setTranslationY为正View整体位置向下偏移，为负则向上偏移。
            textView.setTranslationY(50);
            LogUtil.e("setTranslationY为正，向下挪动");
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 在Activity或者View的onWindowFocusChanged()中获取，其中hasFocus表示当前窗口（Activity或者View）是否获取窗口，true表示获取：
        LogUtil.e("Activity onWindowFocusChanged hasFocus=" +
                hasFocus + "      width=" + textView.getWidth() + "   height=" + textView.getHeight());

    }

    @Override
    protected void onDestroy() {
        if (objectAnimator != null) {
            objectAnimator.end();
            objectAnimator = null;
        }
        super.onDestroy();
    }
}
