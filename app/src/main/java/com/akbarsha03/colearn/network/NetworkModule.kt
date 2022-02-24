package com.akbarsha03.colearn.network

import android.content.Context
import com.akbarsha03.colearn.BuildConfig
import com.akbarsha03.colearn.di.DaggerSet
import com.akbarsha03.colearn.feature.pics.services.UnSplashApiModule
import com.akbarsha03.colearn.network.interceptors.CurlInterceptor
import com.akbarsha03.colearn.network.interceptors.InterceptorModule
import com.akbarsha03.colearn.network.interceptors.LogLevel
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
private annotation class InternalApi

const val ONE_MEGA_BYTE = 1024 * 1024.toLong()

@InstallIn(SingletonComponent::class)
@Module(includes = [UnSplashApiModule::class, InterceptorModule::class])
object NetworkModule {
    private const val BASE_URL_KEY = "base_url_key"
    private const val BASE_URL = "https://api.unsplash.com/"

    @Provides
    @Named(BASE_URL_KEY)
    fun provideBaseUrlString(): String {
        return BASE_URL
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideCurlInterceptor(): CurlInterceptor {
        return CurlInterceptor(logLevel = if (BuildConfig.DEBUG) LogLevel.CURL else LogLevel.NONE)
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(
                ChuckerCollector(
                    context,
                    BuildConfig.DEBUG,
                    RetentionManager.Period.ONE_HOUR
                )
            )
            .maxContentLength(ONE_MEGA_BYTE * 2) // 2 MB
            //.redactHeaders(headersToRedact)
            .alwaysReadResponseBody(true)
            .build()
    }

    // Use newBuilder() to customize so that thread-pool and connection-pool same are used
    @Provides
    fun provideOkHttpClientBuilder(
        @InternalApi okHttpClient: Lazy<OkHttpClient>
    ): OkHttpClient.Builder {
        return okHttpClient.get().newBuilder()
    }

    @InternalApi
    @Provides
    @Singleton
    fun provideBaseOkHttpClient(
        interceptors: DaggerSet<Interceptor>,
        cache: Cache
    ): OkHttpClient {
//        check(Looper.myLooper() != Looper.getMainLooper()) { "HTTP client initialized on main thread." }
        val builder = OkHttpClient.Builder()
        builder.interceptors()
            .addAll(interceptors)
        builder.cache(cache)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
//        check(Looper.myLooper() != Looper.getMainLooper()) { "Cache initialized on main thread." }
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cacheDir = context.cacheDir
        return Cache(cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideJsonConverter(
    ): Moshi {
        val builder = Moshi.Builder()
        return builder
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @InternalApi
        okHttpClient: Lazy<OkHttpClient>,
        moshi: Lazy<Moshi>,
        @Named(BASE_URL_KEY) baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi.get()))
            .callFactory(getCallFactory(okHttpClient))
            .build()
    }

    private fun getCallFactory(okHttpClient: Lazy<OkHttpClient>): (request: Request) -> Call {
        return { okHttpClient.get().newCall(it) }
    }
}
