package com.example.storelabtestapp.data.remote.home

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object HomeRetroFit {
    private fun createRetroFit(): Retrofit {
        val client = OkHttpClient.Builder()
//        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        client.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .build()
    }

    fun createHomeService(): HomeService {
        return createRetroFit().create(HomeService::class.java)
    }
}