package salam.com.myeyepelizer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data implements Parcelable {
    public String dataType;
    public int id;
    public String title;
    public String text;
    public String descrition;
    public String image;
    public String actionUrl;
    public boolean shade;
    public Cover cover;
    public String playUrl;
    public String category;
    public long duration;
    public Header header;
    public List<ItemList> itemList;
    public Author author;
    public String icon;

    private Data(Parcel in){
        dataType=in.readString();
        id=in.readInt();
        title=in.readString();
        text=in.readString();
        descrition=in.readString();
        image=in.readString();
        actionUrl=in.readString();
        shade=in.readByte()!=0;
        cover=in.readParcelable(Cover.class.getClassLoader());
        playUrl=in.readString();
        category=in.readString();
        duration=in.readLong();
        itemList=in.createTypedArrayList(ItemList.CREATOR);
        author=in.readParcelable(Author.class.getClassLoader());
        icon=in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
           parcel.writeString(dataType);
           parcel.writeInt(id);
           parcel.writeString(title);
           parcel.writeString(text);
           parcel.writeString(descrition);
           parcel.writeString(image);
           parcel.writeString(actionUrl);
           parcel.writeByte((byte)(shade?1:0));
           parcel.writeParcelable(cover,flags);
           parcel.writeString(playUrl);
           parcel.writeString(category);
           parcel.writeLong(duration);
           parcel.writeTypedList(itemList);
           parcel.writeParcelable(author,flags);
           parcel.writeString(icon);
    }
    public static final Creator<Data> CREATOR=new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel parcel) {
            return new Data(parcel);
        }

        @Override
        public Data[] newArray(int i) {
            return new Data[i];
        }
    };
}
