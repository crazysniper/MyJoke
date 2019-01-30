package application.scrollerdemo.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
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

        rightBottomScrollerTo = getResources().getDimensionPixelOffset(R.dimen.scrollTofu);
        rightBottomScrollerBy = getResources().getDimensionPixelOffset(R.dimen.scrollByfu);

        leftTopScrollerTo = getResources().getDimensionPixelOffset(R.dimen.scrollTozheng);
        leftTopScrollerBy = getResources().getDimensionPixelOffset(R.dimen.scrollByzheng);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.scrollerTo, R2.id.scrollerBy, R2.id.scrollerToZheng, R2.id.scrollerByZheng,
            R2.id.getInfo, R2.id.textView, R2.id.getLeft, R2.id.getX, R2.id.getX2, R2.id.anim})
    public void onClick(View v) {
        if (v.getId() == R.id.scrollerTo) { // 向右下角scrollerTo
            parent.scrollTo(-rightBottomScrollerTo, -rightBottomScrollerTo);
        } else if (v.getId() == R.id.scrollerBy) { // 向右下角scrollerBy
            parent.scrollBy(-rightBottomScrollerBy, -rightBottomScrollerBy);
        } else if (v.getId() == R.id.scrollerToZheng) { // 向左上角scrollerTo
            parent.scrollTo(leftTopScrollerTo, leftTopScrollerTo);
        } else if (v.getId() == R.id.scrollerByZheng) { // 向左上角scrollerBy
            parent.scrollBy(leftTopScrollerBy, leftTopScrollerBy);
        } else if (v.getId() == R.id.getInfo) {

        } else if (v.getId() == R.id.textView) {
            ToastUtil.getInstance().showToast(ScrollerMainActivity.this, "点击到了");

            String aa = String.format("left=%s,top=%s,right=%s,down=%s", textView.getLeft(), textView.getTop(), textView.getRight(), textView.getBottom());

            String bb = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                bb = String.format("x=%s,y=%s,z=%s", textView.getX(), textView.getY(), textView.getZ());
            } else {
                bb = String.format("x=%s,y=%s", textView.getX(), textView.getY());
            }

            String cc = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                cc = String.format("translationX=%s,translationY=%s,translationZ=%s", textView.getTranslationX(), textView.getTranslationY(), textView.getTranslationZ());
            } else {
                cc = String.format("translationX=%s,translationY=%s", textView.getTranslationX(), textView.getTranslationY());
            }

            LogUtil.e("aa=" + aa);
            LogUtil.e("bb=" + bb);
            LogUtil.e("cc=" + cc);

        } else if (v.getId() == R.id.getLeft) {
            String aa = String.format("left=%s,top=%s,right=%s,down=%s", textView.getLeft(), textView.getTop(), textView.getRight(), textView.getBottom());
            left.setText(aa);
        } else if (v.getId() == R.id.getX) {
            String aa = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                aa = String.format("x=%s,y=%s,z=%s", textView.getX(), textView.getY(), textView.getZ());
            } else {
                aa = String.format("x=%s,y=%s", textView.getX(), textView.getY());
            }
            x.setText(aa);
        } else if (v.getId() == R.id.getX2) {
            String aa = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                aa = String.format("translationX=%s,translationY=%s,translationZ=%s", textView.getTranslationX(), textView.getTranslationY(), textView.getTranslationZ());
            } else {
                aa = String.format("translationX=%s,translationY=%s", textView.getTranslationX(), textView.getTranslationY());
            }
            translationX.setText(aa);
        } else if (v.getId() == R.id.anim) {
            if (objectAnimator == null) {
//                objectAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0, 1);
                objectAnimator = ObjectAnimator.ofFloat(textView, "translationX", 0.0f, 100.0f, 200.0f, 300.0f);

                objectAnimator.setDuration(2000);
                objectAnimator.setRepeatCount(2);
                objectAnimator.setRepeatMode(ValueAnimator.RESTART);
            }

            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    LogUtil.e("update   animatedValue=" + animation.getAnimatedValue());
                }
            });

            objectAnimator.start();
            ToastUtil.getInstance().showToast(ScrollerMainActivity.this, "开启动画");
        }
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
