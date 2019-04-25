package application.notificationdemo.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;

import application.notificationdemo.R;
import application.notificationdemo.R2;
import application.notificationdemo.util.NotificationUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = NotificationUtil.NotificationMainActivity)
public class NotificationMainActivity extends BaseActivity {

    NotificationManager manager = null;

    private int id = 10;
    private String channelId = "channelId";
    private String groupId = "groupId";

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

    @OnClick({R2.id.notificationCompat, R2.id.removeNotificationCompat, R2.id.removeAll,
            R2.id.removeNotificationChannel, R2.id.removeNotificationChannelGroup,
            R2.id.createNotificationChannel})
    public void onClick(View view) {
        if (view.getId() == R.id.notificationCompat) {
            openNotificationCompat();
        } else if (view.getId() == R.id.removeNotificationCompat) {
            removeNotificationCompat();
        } else if (view.getId() == R.id.removeAll) {
            removeAll();
        } else if (view.getId() == R.id.removeNotificationChannel) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannel(channelId);
            }
        } else if (view.getId() == R.id.removeNotificationChannelGroup) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannelGroup(groupId);
            }
        } else if (view.getId() == R.id.createNotificationChannel) { // 创建了两个通知渠道
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "chat";
                String channelName = "聊天消息";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                createNotificationChannel(channelId, channelName, importance);

                channelId = "subscribe";
                channelName = "订阅消息";
                importance = NotificationManager.IMPORTANCE_DEFAULT;
                createNotificationChannel(channelId, channelName, importance);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true); // 表示允许这个渠道下的通知显示角标
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }


    public void sendChatMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }

    public void sendSubscribeMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification))
                .setAutoCancel(true)
                .setNumber(2) // 显示2条数据，桌面图标右上角显示
                .build();
        manager.notify(2, notification);
    }


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
            LogUtil.e("高版本Notification");
            //1.0 建渠道
            NotificationChannel mChannel = new NotificationChannel(channelId, groupId, NotificationManager.IMPORTANCE_HIGH);
            //2.0 把通知渠道通过createNotificationChannel( )方法给状态栏通知的管理类 NotificationManager
            manager.createNotificationChannel(mChannel);
            //3.0 Notification这时候可以正常工作了
            notification = new NotificationCompat.Builder(this, channelId)
//                    .setChannelId(channelId)
                    .setContentTitle("我是标题" + id)//设置通知栏标题
                    .setContentText("我是内容" + id) //设置通知栏显示内容
                    .setWhen(System.currentTimeMillis())//通知产生的时间。
                    // 会在通知信息里显示，通常是系统获取到的时间
                    .setSmallIcon(R.drawable.notification)//设置通知小ICON
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification))//设置通知大ICON
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setTicker("我是测试内容" + id) //首次进入时显示效果
                    .setLights(Color.RED, 2000, 2000)
                    .setVibrate(new long[]{0, 3000, 1000, 3000})
//                    .setDefaults(Notification.DEFAULT_SOUND) //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                    .build();
        } else {
            LogUtil.e("低版本Notification");
            //如果当前Android的版本比Android O（API 26）版本要低
            //直接开始上面的3.0步骤——Notification这时候就可以正常工作了
            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationMainActivity.this, channelId);

            builder.setContentTitle("我是标题" + id)
                    .setContentText("我是内容" + id)   //设置内容
                    .setWhen(System.currentTimeMillis()) //设置通知时间
                    .setSmallIcon(R.drawable.notification) //设置小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification)) //设置大图标
                    .setTicker("我是测试内容" + id) //首次进入时显示效果
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setLights(Color.RED, 2000, 2000)
                    .setVibrate(new long[]{0, 3000, 1000, 3000});
//                    .setDefaults(Notification.DEFAULT_SOUND); //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音

            notification = builder.build();
        }
        manager.notify(id, notification);
        id++;
    }


    public void removeNotificationCompat() {
        manager.cancel(id);
    }

    public void removeAll() {
        manager.cancelAll();
    }
}
