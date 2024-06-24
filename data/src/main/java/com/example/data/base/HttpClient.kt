package com.example.data.base

import com.example.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class HttpClient constructor() {
    fun buildOKHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.HEADERS
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }

        return client.build()
    }

}