package application.webviewdemo.util;

import android.content.Context;

import com.myjoke.baselibray.util.LogUtil;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Gao on 2019/1/22.
 */

public class X5Util {

    public static void initX5(Context context) {
//        QbSdk.setDownloadWithoutWifi(true); //非wifi情况下，主动下载x5内核

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtil.e("开启TBS===X5加速成功   arg0=" + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                LogUtil.e("开启TBS===X5加速失败");

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(context.getApplicationContext(), cb);

    }

}
