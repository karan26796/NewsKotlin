package h.app.hackit.newsapp.kotlin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import h.app.hackit.newsapp.kotlin.a.utils.Constants.NEWS_BASE_URL

/**
 * Created by karan on 5/11/2018.
 */

object NewsApiClient {
    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(NEWS_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return this.retrofit!!
        }
}
