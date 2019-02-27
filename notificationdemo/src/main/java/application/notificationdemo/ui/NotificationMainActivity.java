package application.notificationdemo.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;

import application.notificationdemo.R;
import application.notificationdemo.R2;
import application.notificationdemo.util.NotificationUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = NotificationUtil.NotificationMainActivity)
public class NotificationMainActivity extends BaseActivity {

    NotificationManager manager = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.notificationCompat, R2.id.removeNotificationCompat, R2.id.removeAll})
    public void onClick(View view) {
        if (view.getId() == R.id.notificationCompat) {
            openNotificationCompat();
        } else if (view.getId() == R.id.removeNotificationCompat) {
            removeNotificationCompat();
        } else if (view.getId() == R.id.removeAll) {
            removeAll();
        }
    }

    private String channelId = "channel_001";
    private String name = "name";

    public void openNotificationCompat() {
        Intent intent = new Intent(this, NotificationDetailActivity.class);
        //PendingIntent.getActivity 接收4个参数
        // 第1个参数是Context
        // 第2个参数一般用不到，传0即可
        // 第3个参数是一个Intent对象，可以通过这个对象构建出PendingIntent的意图
        // 这里的Intent表达了我要启动NotificationManager活动的企图
        // 第4个参数是用于确定PendingIntent的行为，有4个默认值，通常情况下传入0即可
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//判断API
            //1.0 建渠道
            NotificationChannel mChannel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT);
            //2.0 把通知渠道通过createNotificationChannel( )方法给状态栏通知的管理类 NotificationManager
            manager.createNotificationChannel(mChannel);
            //3.0 Notification这时候可以正常工作了
            notification = new NotificationCompat.Builder(this, channelId)
                    .setChannelId(channelId)
                    .setContentTitle("我是标题")//设置通知栏标题
                    .setContentText("我是内容") //设置通知栏显示内容
                    .setWhen(System.currentTimeMillis())//通知产生的时间。
                    // 会在通知信息里显示，通常是系统获取到的时间
                    .setSmallIcon(R.drawable.notification)//设置通知小ICON
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification))//设置通知大ICON
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setTicker("我是测试内容") //首次进入时显示效果
                    .setDefaults(Notification.DEFAULT_SOUND) //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                    .build();
        } else {
            //如果当前Android的版本比Android O（API 26）版本要低
            //直接开始上面的3.0步骤——Notification这时候就可以正常工作了
            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationMainActivity.this, channelId);

            builder.setContentTitle("我是标题")
                    .setContentText("我是内容")   //设置内容
                    .setWhen(System.currentTimeMillis()) //设置通知时间
                    .setSmallIcon(R.drawable.notification) //设置小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification)) //设置大图标
                    .setTicker("我是测试内容") //首次进入时显示效果
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND); //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音

            notification = builder.build();
        }
        manager.notify(10, notification);
    }


    public void removeNotificationCompat() {
        manager.cancel(10);
    }

    public void removeAll() {
        manager.cancelAll();
    }
}
