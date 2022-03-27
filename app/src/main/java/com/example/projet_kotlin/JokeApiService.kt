package com.example.projet_kotlin


import io.reactivex.Single
import retrofit2.http.GET

interface JokeApiService
{

    @GET("url_path_extension")
    fun giveMeAJoke(): Single<Joke>

}