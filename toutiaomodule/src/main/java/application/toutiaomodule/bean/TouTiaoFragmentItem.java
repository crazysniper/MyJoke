package application.toutiaomodule.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gao on 2019/1/23.
 */

public class TouTiaoFragmentItem implements Parcelable {
    private String name;
    private String type;

    public TouTiaoFragmentItem(String name) {
        this.name = name;
    }

    public TouTiaoFragmentItem(String name, String url) {
        this.name = name;
        this.type = url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public TouTiaoFragmentItem(Parcel parcel) {
        name = parcel.readString();
        type = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
    }

    public static final Creator CREATOR = new Creator<TouTiaoFragmentItem>() {
        @Override
        public TouTiaoFragmentItem createFromParcel(Parcel source) {
            return new TouTiaoFragmentItem(source);
        }

        @Override
        public TouTiaoFragmentItem[] newArray(int size) {
            return new TouTiaoFragmentItem[0];
        }
    };
}