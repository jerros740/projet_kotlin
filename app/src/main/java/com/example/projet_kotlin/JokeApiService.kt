package com.example.projet_kotlin


import io.reactivex.Observable
import retrofit2.http.GET

interface JokeApiService
{

    @GET("random")
    fun giveMeAJoke(): Observable<Joke>

}