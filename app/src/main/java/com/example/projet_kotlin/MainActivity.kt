package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitJoke = JokeApiServiceFactory().getJokeApiService()

        val retrofitData: Single<Joke> = retrofitJoke.giveMeAJoke()

        // Affichage de la requête
        retrofitData
            .subscribeOn(Schedulers.io())
            .subscribe { result -> println(result) }


    }


    fun initRecycler(list: List<Joke>, context: Context)
    {
        var adapter = JokeAdapter(list,context)
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerViewJokes)

        recyclerview.layoutManager = LinearLayoutManager(this)
        // Création du recycler view
        recyclerview.adapter = adapter
    }
}


