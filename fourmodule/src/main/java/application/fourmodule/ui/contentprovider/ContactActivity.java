package application.fourmodule.ui.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.myjoke.baselibray.json.JsonUtil;
import com.myjoke.baselibray.util.LogUtil;
import com.myjoke.baselibray.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import application.fourmodule.R;
import application.fourmodule.ui.contentprovider.bean.ContactBean;
import application.fourmodule.util.FourModuleConstant;
import butterknife.ButterKnife;

@Route(path = FourModuleConstant.ContactActivity)
public class ContactActivity extends BaseActivity {

    private int CONTACT_REQUEST_CODE = 11;
    private final String CONTACTS_URI = "content://com.android.contacts/contacts";


    @Override
    public int getLayoutId() {
        return R.layout.activity_contact;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int check = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
            if (check == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_REQUEST_CODE);
            }
        } else {
            getContacts();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACT_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            } else {
                boolean should = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS);
                if (!should) {
                    ToastUtil.getInstance().showToast(this, "使用联系人要开通联系人权限哦~");
                }
            }
        }
    }


    public void getContacts() {
        List<ContactBean> dataList = new ArrayList<>();


        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        int columnCount = cursor.getColumnCount();
        int count = cursor.getCount();

        LogUtil.e("列数=" + columnCount);
        LogUtil.e("行数=" + count);

//        if (cursor.moveToFirst()) {
//            do {
//                String value = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                LogUtil.e("id=" + id + "     ,value =" + value);
//            } while (cursor.moveToNext());
//        }


        if (cursor != null) {
            ContactBean contactBean = null;
            while (cursor.moveToNext()) {
                contactBean = new ContactBean();
                String value = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                long contactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                LogUtil.e("contactId=" + contactId + "     ,value =" + value);

                contactBean.setId(contactId);
                contactBean.setName(value);

                // 根据联系人的id查询电话号码
                Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        new String[]{contactId + ""}, null);

                List<ContactBean.Phone> phoneList = new ArrayList<>();
                if (phoneCursor != null) {
                    ContactBean.Phone phone = null;

                    while (phoneCursor.moveToNext()) {
                        phone = new ContactBean.Phone();
                        String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int type = phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        long phoneId = phoneCursor.getLong(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));

                        switch (type) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                LogUtil.e("手机=" + number + "     ,type =" + type);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                LogUtil.e("单位=" + number + "     ,type =" + type);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                LogUtil.e("住宅=" + number + "     ,type =" + type);
                                break;
                        }

                        phone.setId(phoneId);
                        phone.setNumber(number);
                        phone.setType(type);
                        phoneList.add(phone);
                    }
                    phoneCursor.close();

                }
                contactBean.setPhoneList(phoneList);


                // 根据联系人的id查询邮箱信息
                Cursor emailCursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        new String[]{contactId + ""}, null);
                if (emailCursor != null) {
                    while (emailCursor.moveToNext()) {
                        String name = emailCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));
                        String address = emailCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                        int type = emailCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));

                        switch (type) {
                            case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
                                LogUtil.e("name=" + name + "     ,address=" + address + "     ,type=home");
                                break;
                            case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
                                LogUtil.e("name=" + name + "     ,address=" + address + "     ,type=work");
                                break;
                            case ContactsContract.CommonDataKinds.Email.TYPE_MOBILE:
                                LogUtil.e("name=" + name + "     ,address=" + address + "     ,type=mobile");
                                break;
                            case ContactsContract.CommonDataKinds.Email.TYPE_OTHER:
                                LogUtil.e("name=" + name + "     ,address=" + address + "     ,type=other");
                                break;
                        }
                    }
                    emailCursor.close();
                }
                LogUtil.e(JsonUtil.getInstance().toJson(contactBean));
                dataList.add(contactBean);
            }
            cursor.close();
        }
        LogUtil.e(JsonUtil.getInstance().toJson(dataList));
    }

}
