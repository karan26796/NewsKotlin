package h.app.hackit.newsapp.kotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
data class News(
        @SerializedName("source") val source: News.Source,
        @SerializedName("author") val author: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("url") val url: String,
        @SerializedName("urlToImage") val urlToImage: String,
        @SerializedName("publishedAt") val date: String,
        val isBookmarked: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(News.Source::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(source, flags)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(date)
        parcel.writeByte(if (isBookmarked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }

    data class Source(@SerializedName("name") var name: String,
                      @SerializedName("id") var id: String) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(id)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Source> {
            override fun createFromParcel(parcel: Parcel): Source {
                return Source(parcel)
            }

            override fun newArray(size: Int): Array<Source?> {
                return arrayOfNulls(size)
            }
        }
    }
}