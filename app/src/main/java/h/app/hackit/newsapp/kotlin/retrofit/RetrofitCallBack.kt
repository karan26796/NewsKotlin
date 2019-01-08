package h.app.hackit.newsapp.kotlin.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallBack<T>(private val listener: RetrofitCallbackListener<T>) : Callback<T> {

    interface RetrofitCallbackListener<T> {
        fun onSuccess(call: Call<T>, response: Response<T>)

        fun onFailure(call: Call<T>, t: Throwable)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        listener.onSuccess(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        listener.onFailure(call, t)
    }
}
