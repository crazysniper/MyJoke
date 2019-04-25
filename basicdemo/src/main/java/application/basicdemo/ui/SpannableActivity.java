package application.basicdemo.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import application.basicdemo.R;
import application.basicdemo.R2;
import application.basicdemo.bean.Topic;
import application.basicdemo.bean.User;
import application.basicdemo.util.BasicUtil;
import application.basicdemo.util.SpanUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = BasicUtil.SpannableActivity)
public class SpannableActivity extends BaseActivity {

    @BindView(R2.id.foreground)
    TextView foregroundView;
    @BindView(R2.id.background)
    TextView backgroundView;
    @BindView(R2.id.relativeSizeSpan)
    TextView relativeSizeView;
    @BindView(R2.id.strikethroughSpan)
    TextView strikethroughView;
    @BindView(R2.id.underlineSpan)
    TextView underlineView;
    @BindView(R2.id.superscriptSpan)
    TextView superscriptView;

    @BindView(R2.id.subscriptSpan)
    TextView subscriptView;

    @BindView(R2.id.styleSpan)
    TextView styleView;


    @BindView(R2.id.imageSpan)
    TextView imageView;
    @BindView(R2.id.clickableSpan)
    TextView clickView;
    @BindView(R2.id.urlSpan)
    TextView urlView;

    @BindView(R2.id.tv_keyword_colored)
    TextView tvColoredKeywd;
    @BindView(R2.id.tv_topic)
    TextView tvTopic;
    @BindView(R2.id.tv_test_at)
    TextView tvTestAt;
    @BindView(R2.id.tv_text_expression)
    TextView tvExpression;

    @Override
    public int getLayoutId() {
        return R.layout.activity_spannable;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        SpannableString spannableString = new SpannableString("设置文字的前景色为淡蓝色");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(foregroundColorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        foregroundView.setText(spannableString);


        spannableString = new SpannableString("设置文字的背景色为淡绿色");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#AC00FF30"));
        spannableString.setSpan(backgroundColorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        backgroundView.setText(spannableString);

        spannableString = new SpannableString("万丈高楼平地起");

        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(1.8f);
        RelativeSizeSpan sizeSpan05 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan07 = new RelativeSizeSpan(1.2f);

        spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan02, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan03, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan04, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan05, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan06, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan07, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        relativeSizeView.setText(spannableString);


        spannableString = new SpannableString("为文字设置删除线");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        strikethroughView.setText(spannableString);


        spannableString = new SpannableString("为文字设置下划线");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        underlineView.setText(spannableString);


        spannableString = new SpannableString("为文字设置上标");
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableString.setSpan(superscriptSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        superscriptView.setText(spannableString);

        spannableString = new SpannableString("为文字设置下标");
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        spannableString.setSpan(subscriptSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        subscriptView.setText(spannableString);


        spannableString = new SpannableString("为文字设置粗体、斜体风格");
        StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan_I = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleSpan_B, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan_I, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        styleView.setHighlightColor(Color.parseColor("#36969696"));
        styleView.setText(spannableString);


        spannableString = new SpannableString("在文本中添加表情（表情）");
        Drawable drawable = getResources().getDrawable(R.drawable.bg);
        drawable.setBounds(0, 0, 42, 42);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        imageView.setText(spannableString);


        spannableString = new SpannableString("为文字设置点击事件");
        MyClickableSpan clickableSpan = new MyClickableSpan("http://www.jianshu.com/users/dbae9ac95c78");
        UnderlineSpan underlineSpan1 = new UnderlineSpan();
        spannableString.setSpan(clickableSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(underlineSpan1, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        clickView.setMovementMethod(LinkMovementMethod.getInstance());
        clickView.setHighlightColor(Color.parseColor("#36969696"));
        clickView.setText(spannableString);

        spannableString = new SpannableString("为文字设置超链接");
        URLSpan urlSpan = new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
        spannableString.setSpan(urlSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        urlView.setMovementMethod(LinkMovementMethod.getInstance());
        urlView.setHighlightColor(Color.parseColor("#36969696"));
        urlView.setText(spannableString);


        testColoredKeywd();
        testTopic();
        textAtUsers();
        textExpression();
    }

    /**
     * 关键字变色
     */
    private void testColoredKeywd() {
        String string = "Android一词的本义指“机器人”，同时也是Google于2007年11月5日,Android logo相关图片,Android logo相关图片(36张)\n";
        SpannableString cardText = null;
        try {
            cardText = SpanUtils.getKeyWordSpan(getResources().getColor(R.color.md_green_600), string, "Android");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvColoredKeywd.setText(cardText);
    }

    /**
     * 测试话题
     */
    private void testTopic() {
        String topic = "#舌尖上的大连#四种金牌烤芝士吃法爱吃芝士的盆友不要错过了~L秒拍视频\n";
        SpannableString topicText = null;
        try {
            topicText = SpanUtils.getTopicSpan(Color.BLUE, topic, true, new SpanUtils.SpanClickListener<Topic>() {
                @Override
                public void onSpanClick(Topic t) {
                    Toast.makeText(SpannableActivity.this, "点击话题:" + t.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Topic(1, "舌尖上的大连"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvTopic.setText(topicText);
        //如果想实现点击，必须要设置这个
        tvTopic.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 测试@好友
     */
    private void textAtUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "好友1"));
        users.add(new User(2, "好友2"));
        StringBuilder sb = new StringBuilder("快来看看啊");
        for (User u : users) {
            sb.append("@").append(u.getName());
        }
        sb.append("\n");
        try {
            SpannableString atSpan = SpanUtils.getAtUserSpan(Color.BLUE, sb.toString(), true, new SpanUtils.SpanClickListener<User>() {
                @Override
                public void onSpanClick(User user) {
                    Toast.makeText(SpannableActivity.this, "@好友:" + user.toString(), Toast.LENGTH_SHORT).show();
                }
            }, users);

            tvTestAt.setText(atSpan);
            tvTestAt.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试表情显示
     */
    private void textExpression() {
        String exStr = "今天天气很好啊[呲牙],是不是应该做点什么[色]";
        SpannableString span = null;
        try {
            span = SpanUtils.getExpressionSpan(this, exStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvExpression.setText(span);
    }


    class MyClickableSpan extends ClickableSpan {

        private String content;

        public MyClickableSpan(String content) {
            this.content = content;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            ToastUtil.getInstance().showToast("content==" + content);
        }
    }
}