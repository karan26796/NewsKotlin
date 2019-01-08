package h.app.hackit.newsapp.kotlin.retrofit

import h.app.hackit.newsapp.kotlin.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by karan on 5/11/2018.
 */

interface NewsApiInterface {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("category") category: String,
                        @Query("pageSize") size: Int,
                        @Query("apiKey") apiKey: String): Call<NewsResponse>

    @GET("top-headlines")
    fun getNewsSource(@Query("sources") sources: String,
                      @Query("apiKey") apiKey: String): Call<NewsResponse>

    @GET("top-headlines")
    fun getNewsSearch(@Query("q") search: String,
                      @Query("apiKey") apiKey: String): Call<NewsResponse>
}
