package h.app.hackit.newsapp.kotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by karan on 5/11/2018.
 */

class NewsResponse(@SerializedName("status")
                   val status: String,
                   @SerializedName("articles")
                   val articles: List<News>,
                   @SerializedName("totalResults")
                   val totalResults: Int)

