package application.systeminfo.ui;

import android.graphics.Rect;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.ToastUtil;

import java.util.Locale;

import application.baseview.layout.MyLinearView;
import application.systeminfo.R;
import application.systeminfo.R2;
import application.systeminfo.util.SystemInfoConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = SystemInfoConstant.DeviceDetailActivity)
public class DeviceDetailActivity extends BaseActivity implements TextToSpeech.OnInitListener {

    @BindView(R2.id.deviceWidth)
    MyLinearView deviceWidth;
    @BindView(R2.id.deviceHeight)
    MyLinearView deviceHeight;


    @BindView(R2.id.appWidth)
    MyLinearView appWidth;
    @BindView(R2.id.appHeight)
    MyLinearView appHeight;

    @BindView(R2.id.statusBarHeight)
    MyLinearView statusBarHeight;

    @BindView(R2.id.textToSpeech)
    MyLinearView textToSpeechBtn;

    @BindView(R2.id.getInfo)
    Button getInfo;

    private TextToSpeech textToSpeech;

    @Override
    public int getLayoutId() {
        return R.layout.activity_device_detail;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        deviceWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().showToast("1111222");
            }
        });

        textToSpeechBtn.setClickable(true);
        textToSpeech = new TextToSpeech(this, this);
    }

    @OnClick({R2.id.getInfo, R2.id.deviceHeight, R2.id.appWidth, R2.id.appHeight, R2.id.statusBarHeight, R2.id.textToSpeech})
    public void onClick(View v) {
        ToastUtil.getInstance().showToast("1111");
        if (v.getId() == R.id.getInfo) {
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            deviceWidth.setRightText("" + widthPixels);

            int heightPixels = getResources().getDisplayMetrics().heightPixels;
            deviceHeight.setRightText("" + heightPixels);


            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);


            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int statusHeight = rect.top;


            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int a = getResources().getDimensionPixelSize(resourceId);


            statusBarHeight.setRightText("" + statusHeight + "____" + a);


            if (textToSpeech != null && !textToSpeech.isSpeaking()) {
                // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                textToSpeech.setPitch(1.0f);
                //设定语速 ，默认1.0正常语速
                textToSpeech.setSpeechRate(1.0f);
                //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
                textToSpeech.speak(textToSpeechBtn.getLeftText(), TextToSpeech.QUEUE_FLUSH, null);
            }

        } else if (v.getId() == R.id.textToSpeech) {
            Toast.makeText(this, "11111111111111", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
        textToSpeech.shutdown(); // 关闭，释放资源
    }
}
