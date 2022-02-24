package com.akbarsha03.colearn.network

import okhttp3.Call
import okhttp3.Request
import retrofit2.Retrofit

@PublishedApi
internal inline fun Retrofit.Builder.callFactory(
    crossinline body: (Request) -> Call
) = callFactory { request -> body(request) }