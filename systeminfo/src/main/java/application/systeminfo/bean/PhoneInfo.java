package application.systeminfo.bean;

import java.io.Serializable;

/**
 * Created by Gao on 2018/12/6.
 */

public class PhoneInfo implements Serializable {

    private String deviceModel;
    private String deviceBrand;
    private String subscriberId;

    private String deviceId;
    private String imei;
    private String meid;

    private String systemVersion;
    private int systemVersionCode;

    private AppInfo appInfo;

    public PhoneInfo(String deviceBrand, String deviceModel, int systemVersionCode, String systemVersion) {
        this.deviceBrand = deviceBrand;
        this.deviceModel = deviceModel;
        this.systemVersionCode = systemVersionCode;
        this.systemVersion = systemVersion;
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "appInfo=" + appInfo +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", imei='" + imei + '\'' +
                ", meid='" + meid + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", systemVersionCode=" + systemVersionCode +
                '}';
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public int getSystemVersionCode() {
        return systemVersionCode;
    }

    public void setSystemVersionCode(int systemVersionCode) {
        this.systemVersionCode = systemVersionCode;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }
}
