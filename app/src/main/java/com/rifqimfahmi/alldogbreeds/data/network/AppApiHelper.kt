package com.rifqimfahmi.alldogbreeds.data.network

import android.content.Context
import com.rifqimfahmi.alldogbreeds.di.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

class AppApiHelper @Inject constructor() : ApiHelper {
    var mDogApi: DogApi
    var mGiphyApi: GiphyApi

    // TODO : change use dagger instead
    init {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClient.readTimeout(12, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(12, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(12, TimeUnit.SECONDS)

        val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient.addInterceptor(httpLoggingInterceptor)

        var retrofit: Retrofit.Builder = Retrofit.Builder()
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        mDogApi = retrofit.baseUrl(DogApi.ENDPOINT).build()
                    .create(DogApi::class.java)

        mGiphyApi = retrofit.baseUrl(GiphyApi.ENDPOINT).build()
                .create(GiphyApi::class.java)
    }

    override fun getDogApi(): DogApi = mDogApi

    override fun getGiphyApi(): GiphyApi = mGiphyApi
}