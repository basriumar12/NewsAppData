package com.basri.newsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiRetrofit {
    private var retrofit: Retrofit? = null
     fun getClient(): Retrofit? {
           if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}