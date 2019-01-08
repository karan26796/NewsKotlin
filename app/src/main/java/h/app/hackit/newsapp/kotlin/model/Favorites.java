package h.app.hackit.newsapp.kotlin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorites implements Parcelable {
    private static final String TAG = Favorites.class.getSimpleName();

    public String title;
    public String url;
    public String imgUrl;
    public String source;
    public String desc;
    public String date;
    public long id;

    public Favorites() {

    }

    protected Favorites(Parcel in) {
        title = in.readString();
        url = in.readString();
        imgUrl = in.readString();
        source = in.readString();
        desc = in.readString();
        date = in.readString();
        id = in.readLong();
    }

    public static final Creator<Favorites> CREATOR = new Creator<Favorites>() {
        @Override
        public Favorites createFromParcel(Parcel in) {
            return new Favorites(in);
        }

        @Override
        public Favorites[] newArray(int size) {
            return new Favorites[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(imgUrl);
        dest.writeString(source);
        dest.writeString(desc);
        dest.writeString(date);
        dest.writeLong(id);
    }
}
