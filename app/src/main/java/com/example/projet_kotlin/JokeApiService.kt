package com.example.projet_kotlin


import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface JokeApiService
{

    @GET("random")
    fun giveMeAJoke(): Observable<Joke>

}