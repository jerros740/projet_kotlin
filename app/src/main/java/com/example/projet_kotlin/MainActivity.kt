package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null
    private var adapter: JokeAdapter? = null
    private var jokeFactory = JokeApiServiceFactory().getJokeApiService()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        val list = mutableListOf<Joke>()

        setContentView(R.layout.activity_main)
        setListener()
        setRecycler(list,context)

        addJoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }

    fun addJoke() {
        var loadingProgressBar = findViewById<ProgressBar>(R.id.loading)
        val retrofitData: Observable<Joke> = jokeFactory.giveMeAJoke()
        loadingProgressBar.visibility = View.VISIBLE
        var disposable = retrofitData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ result -> var joke = result
                adapter?.addItem(joke)
                loadingProgressBar.visibility = View.INVISIBLE
            }

        // Add to disposable when creating view
        compositeDisposable?.add(disposable)
    }

    fun setRecycler(list: List<Joke>, context: Context) {
        adapter = JokeAdapter(list,context)
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerViewJokes)

        recyclerview.layoutManager = LinearLayoutManager(this)
        // Création du recycler view
        recyclerview.adapter = adapter
    }

    fun setListener() {
        var btnAddJoke = findViewById<Button>(R.id.btnAddJoke)
        btnAddJoke.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                addJoke()
            }
        })
    }
}





