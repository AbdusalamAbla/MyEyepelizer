package salam.com.myeyepelizer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemList implements Parcelable {
    private String type;
    public Data data;

    private ItemList(Parcel in) {
        type = in.readString();
        data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {
        @Override
        public ItemList createFromParcel(Parcel in) {
            return new ItemList(in);
        }

        @Override
        public ItemList[] newArray(int size) {
            return new ItemList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeParcelable(data, i);
    }
}
