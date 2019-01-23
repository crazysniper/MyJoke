package application.newsmodule.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gao on 2019/1/23.
 */

public class NewsFragmentItem implements Parcelable {
    private String name;
    private String url;

    public NewsFragmentItem(String name) {
        this.name = name;
    }

    public NewsFragmentItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static Creator getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public NewsFragmentItem(Parcel parcel) {
        name = parcel.readString();
        url = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }

    public static final Parcelable.Creator CREATOR = new Creator<NewsFragmentItem>() {
        @Override
        public NewsFragmentItem createFromParcel(Parcel source) {
            return new NewsFragmentItem(source);
        }

        @Override
        public NewsFragmentItem[] newArray(int size) {
            return new NewsFragmentItem[0];
        }
    };
}