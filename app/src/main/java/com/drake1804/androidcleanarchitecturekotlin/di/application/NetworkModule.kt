package com.drake1804.androidcleanarchitecturekotlin.di.application

import com.drake1804.androidcleanarchitecturekotlin.BuildConfig
import com.drake1804.androidcleanarchitecturekotlin.data.rest.RestApi
import com.drake1804.androidcleanarchitecturekotlin.data.rest.RestService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by drake1804 on 5/19/17.
 */
@Module
class NetworkModule {

    private val BASE_URL: String = "https://jsonplaceholder.typicode.com"

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE)).build()

    @Provides
    @Singleton
    fun provideRestApi(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRestService(restApi: RestApi) = RestService(restApi)
}