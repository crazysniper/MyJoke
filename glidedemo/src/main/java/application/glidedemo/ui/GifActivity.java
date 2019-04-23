package application.glidedemo.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myjoke.baselibray.base.BaseActivity;

import application.glidedemo.R;
import application.glidedemo.R2;
import application.glidedemo.util.GlideUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = GlideUtil.GifActivity)
public class GifActivity extends BaseActivity {

    @BindView(R2.id.start)
    Button start;

    @BindView(R2.id.gif)
    ImageView gifView;

    private String gifUrl = "http://wx2.sinaimg.cn/large/006tbyQzly1g12h21ujvng305008we82.gif";

    @Override
    public int getLayoutId() {
        return R.layout.activity_gif;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.start})
    public void onClick(View view) {
        if (view.getId() == R.id.start) {
            RequestOptions options=  new RequestOptions().placeholder(R.drawable.btn_shape).diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(this).load(gifUrl).apply(options).into(gifView);
            Glide.with(this).load(gifUrl).preload();
        }
    }
}
