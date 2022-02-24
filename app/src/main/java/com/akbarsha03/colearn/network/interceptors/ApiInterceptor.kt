package com.akbarsha03.colearn.network.interceptors

import android.util.Log
import com.akbarsha03.colearn.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val existingHeaders = chain.request().headers

        if (BuildConfig.API_KEY.isEmpty()) {
            throw RuntimeException("API key doesn't exist")
        }

        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")

//        for (existingHeader in existingHeaders) {
//            Log.d("", "curl header ${existingHeader.first}: ${existingHeader.second}")
//            if (existingHeader.second == null || existingHeader.second == "null") {
//                requestBuilder.removeHeader(existingHeader.first)
//            }
//        }

        return chain.proceed(requestBuilder.build())
    }
}
