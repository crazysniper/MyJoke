package application.camerademo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;

import application.camerademo.R;
import application.camerademo.R2;
import application.camerademo.util.CameraUtil;
import application.camerademo.util.PhotoUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = CameraUtil.CameraMainActivity)
public class CameraMainActivity extends BaseActivity {

    @BindView(R2.id.img)
    ImageView img;

    @BindView(R2.id.path)
    TextView path;

    private File cameraSavePath;//拍照照片路径
    private Uri uri;

    @Override
    public int getLayoutId() {
        return R.layout.activity_camera_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");

    }

    @OnClick({R2.id.openCamera, R2.id.openAlbum, R2.id.openAlbum2})
    public void onClick(View view) {
        if (view.getId() == R.id.openCamera) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.CAMERA)
                    .onGranted(permissions -> {
                        ToastUtil.getInstance().showToast("打开相机");
                        goCamera();
                    })
                    .onDenied(permissions -> {
                        ToastUtil.getInstance().showToast("没有获取到相机权限");
                    })
                    .start();
        } else if (view.getId() == R.id.openAlbum) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.STORAGE)
                    .onGranted(permissions -> {
                        ToastUtil.getInstance().showToast("打开相册");
                        goPhotoAlbum();
                    })
                    .onDenied(permissions -> {
                        ToastUtil.getInstance().showToast("没有获取到相册权限");
                    })
                    .start();
        } else if (view.getId() == R.id.openAlbum2) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.STORAGE)
                    .onGranted(permissions -> {
                        ToastUtil.getInstance().showToast("打开相册");
                    })
                    .onDenied(permissions -> {
                        ToastUtil.getInstance().showToast("没有获取到相册权限");
                    })
                    .start();
        }
    }

    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!(intent.resolveActivity(getPackageManager()) != null)) {
            ToastUtil.getInstance().showToast("intent异常，不能打开");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(CameraMainActivity.this, "application.camerademo.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        CameraMainActivity.this.startActivityForResult(intent, 1);
    }


    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            LogUtil.e("拍照返回图片路径:", photoPath);
            path.setText(photoPath);
            Glide.with(CameraMainActivity.this).load(photoPath).into(img);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = PhotoUtil.getRealPathFromUri(this, data.getData());
            path.setText(photoPath);
            Glide.with(CameraMainActivity.this).load(photoPath).into(img);
        }
    }
}
