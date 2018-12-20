package application.permissiondemo.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.OsUtil;
import com.myjoke.baselibray.util.ToastUtil;

import application.permissiondemo.R;
import application.permissiondemo.util.PermissionConstant;

@Route(path = PermissionConstant.YuanShengPermissionActivity)
public class YuanShengPermissionActivity extends BaseActivity implements View.OnClickListener {

    private Button openCamera, openSD, openContact, openSelfSetting, openSelfPermissionSetting;
    private ImageView img;


    private boolean needCheckPermission = false;
    private final int PERMISSION_REQUEST_CODE_CAMERA = 10;


    private final int REQUEST_CODE_OPEN_CAMERA = 10;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yuan_sheng_permission;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        openCamera = findView(R.id.openCamera);
        openSD = findView(R.id.openSD);
        openContact = findView(R.id.openContact);
        img = findView(R.id.img);
        openSelfSetting = findView(R.id.openSelfSetting);
        openSelfPermissionSetting = findView(R.id.openSelfPermissionSetting);

        openCamera.setOnClickListener(this);
        openSD.setOnClickListener(this);
        openContact.setOnClickListener(this);
        openSelfSetting.setOnClickListener(this);
        openSelfPermissionSetting.setOnClickListener(this);

        int aa = getPackageManager().checkPermission(Manifest.permission.CAMERA, getPackageName());

        LogUtil.e("YuanShengPermissionActivity  aaaaaaaaaaa==" + aa);
    }

    @Override
    public void initData() {
        checkSDKVersionCode();
    }

    //  判断SDK是否在6.0以上
    private void checkSDKVersionCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            needCheckPermission = true;
        } else {
            needCheckPermission = false;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.openSelfSetting) { // 打开本应用基础信息设置
            getAppDetailSettingIntent(intent);
            startActivity(intent);
        } else if (v.getId() == R.id.openSelfPermissionSetting) { // 打开本应用权限设置
            String rom = OsUtil.getMiuiVersion();
            LogUtil.e("YuanShengPermissionActivity  goMiaoMiMainager --- rom : " + rom);
            try { // MIUI 8及以上版本
                intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                intent.putExtra("extra_pkgname", getPackageName());
                LogUtil.e("YuanShengPermissionActivity  aaaaaaaaaaa");
                startActivity(intent);
            } catch (Exception e) {
                try { // MIUI 5/6/7
                    intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    intent.putExtra("extra_pkgname", getPackageName());
                    LogUtil.e("YuanShengPermissionActivity  bbbbbbbb");
                    startActivity(intent);
                } catch (Exception e1) { // 否则跳转到应用详情
                    LogUtil.e("YuanShengPermissionActivity  cccccccccc");
                }
            }
        } else if (v.getId() == R.id.openCamera) {
            if (needCheckPermission) {

                boolean should = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                LogUtil.e("YuanShengPermissionActivity   111  shouldShowRequestPermissionRationale should=" + should);

                int hasPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                if (hasPermission == PackageManager.PERMISSION_GRANTED) {
//                    ToastUtil.getInstance().showToast(this, "已经获得相机授权了");
                    LogUtil.e("YuanShengPermissionActivity      已经获得相机授权了");
                    openCamera();
                } else {
//                    ToastUtil.getInstance().showToast(this, "没有获得相机授权了，需要申请权限");
                    LogUtil.e("YuanShengPermissionActivity      没有获得相机授权了，需要申请权限");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE_CAMERA);
                }
            } else {
                openCamera();
            }
        } else if (v.getId() == R.id.openSD) {
            if (needCheckPermission) {

            } else {

            }
        } else if (v.getId() == R.id.openContact) {
            if (needCheckPermission) {

            } else {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    ToastUtil.getInstance().showToast(this, "申请到相机授权了");
                    LogUtil.e("YuanShengPermissionActivity      申请到相机授权了");
                    openCamera();
                } else {
//                    ToastUtil.getInstance().showToast(this, "没有申请到相机授权");
                    LogUtil.e("YuanShengPermissionActivity      没有申请到相机授权");

                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                    LogUtil.e("YuanShengPermissionActivity     shouldShowRequestPermissionRationale should=" + should);
                    if (!should) {
                        ToastUtil.getInstance().showToast(this, "使用相机要开通相机权限哦~");
                    }
                }
                break;
        }
    }

    // 拍照的照片的存储位置
    private String mTempPhotoPath;
    // 照片所在的Uri地址
    private Uri imageUri;

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";
        // 获取图片所在位置的Uri路径    *****这里为什么这么做参考问题2*****
//                imageUri = Uri.fromFile(new File(mTempPhotoPath));
//        imageUri = FileProvider.getUriForFile(YuanShengPermissionActivity.this,
//                YuanShengPermissionActivity.this.getApplicationContext().getPackageName() + ".my.provider",
//                new File(mTempPhotoPath));
//        //下面这句指定调用相机拍照后的照片存储的路径
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(intent, REQUEST_CODE_OPEN_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e("YuanShengPermissionActivity  onActivityResult requestCode=" + requestCode + "    resultCode=" + resultCode);
        if (requestCode == REQUEST_CODE_OPEN_CAMERA && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.getParcelable("data");
            img.setImageBitmap(bitmap);
        }
    }


    private Intent getAppDetailSettingIntent(Intent localIntent) {
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 要加上这一行
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }


}
