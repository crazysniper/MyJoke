package application.systeminfo.bean;

import java.io.Serializable;

/**
 * Created by Gao on 2018/12/6.
 */

public class AppInfo implements Serializable {

    private String packageName;
    private int versionCode;
    private String versionName;

    public AppInfo() {
    }

    public AppInfo(String packageName, int versionCode, String versionName) {
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "packageName='" + packageName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
