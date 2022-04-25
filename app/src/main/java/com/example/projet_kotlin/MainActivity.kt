package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jokeFactory = JokeApiServiceFactory().getJokeApiService()

        val retrofitData: Observable<Joke> = jokeFactory.giveMeAJoke()

        val context = this
        val list = mutableListOf<Joke>()
        // Affichage de la requête

        var disposable = retrofitData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ result -> var joke = result
                list.add(joke)
                initRecycler(list,context)
            }

        // Add to disposable when creating view
        compositeDisposable?.add(disposable)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        compositeDisposable?.dispose()
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





