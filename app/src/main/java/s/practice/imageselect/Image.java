package s.practice.imageselect;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dagou on 2017/10/17.
 */

public class Image implements Parcelable{
   public String path;
    public  String name;
    public int position;
    public  boolean isSelected = false;

    public Image() {

    }

    protected Image(Parcel in) {
        path = in.readString();
        name = in.readString();
        position = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }

    @Override
    public int describeContents() {
        return position;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(name);
        parcel.writeInt(position);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }
}
