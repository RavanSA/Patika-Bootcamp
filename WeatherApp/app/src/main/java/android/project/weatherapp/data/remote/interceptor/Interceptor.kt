package android.project.weatherapp.data.remote.interceptor

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class WeatherInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain
            .request()
            .newBuilder()
            .header("X-Api-Key", "f144e0364830419ff0f8569ebd76e4e0")

        return chain.proceed(requestBuilder.build())
    }

}