package com.example.projet_kotlin

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JokeApiServiceFactory
{
    fun getJokeApiService(): JokeApiService
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/jokes/random/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(JokeApiService::class.java)
    }
}


