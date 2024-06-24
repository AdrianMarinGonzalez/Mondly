package com.example.data.base

import com.example.data.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance constructor(private val client: HttpClient) {

    fun get(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client.buildOKHttpClient())
            .addConverterFactory(provideGson())
            .build()
    }

    private fun provideGson(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().create())
}