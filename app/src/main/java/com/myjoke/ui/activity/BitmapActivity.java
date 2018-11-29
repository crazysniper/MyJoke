package com.myjoke.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.R;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.util.ConstantPath;

@Route(path = ConstantPath.BitmapActivity)
public class BitmapActivity extends BaseActivity implements View.OnClickListener {
    @Autowired
    public String key1;
    @Autowired(name = "intKey")// 通过name来映射URL中的不同参数
    public int intValue;
    Button btnUrl, toThird;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bitmap;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        btnUrl = findView(R.id.btn_url);
        toThird = findView(R.id.toThird);

        btnUrl.setOnClickListener(this);
        toThird.setOnClickListener(this);
    }

    @Override
    public void initData() {
        LogUtil.e("key1==" + key1);
        LogUtil.e("intValue==" + intValue);
        testBitmap();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_url:

//                Intent intent = new Intent(BitmapActivity.this, BitmapActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivityForResult(intent);
//                startActivity(new Intent(BitmapActivity.this, BitmapActivity.class));
                ARouter.getInstance().build(ConstantPath.BitmapActivity).navigation(this);
                break;
            case R.id.toThird:
//                startActivityForResult(new Intent(BitmapActivity.this, ThirdActivity.class),2222);
//                startActivity(new Intent(BitmapActivity.this, ThirdActivity.class));
                ARouter.getInstance().build(ConstantPath.ThirdActivity).navigation(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e("BaseActivity", clazzName + " requestCode=" + requestCode + "  resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void testBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        // 如果createBitmap的宽、高还是和原来的一样的话，bitmap2就是不可以mutable的。
        // 如果宽、高和原来的不一样，bitmap2就是可以mutable的。
        LogUtil.e("bitmap2===bitmap,," + Boolean.toString(bitmap2 == bitmap));// true
        LogUtil.e("bitmap.isMutable==" + bitmap.isMutable()); // false
        LogUtil.e("bitmap2.isMutable==" + bitmap2.isMutable()); // false
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "result");
        setResult(200, intent);
        super.onBackPressed();
    }
}
