package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
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
    private var list:java.util.ArrayList<Joke> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loadingProgressBar = findViewById<ProgressBar>(R.id.loading)
        loadingProgressBar.visibility = View.INVISIBLE
        setListener()

        if (savedInstanceState == null) {
            val context = this
            setRecycler(list,context)
            addJokes(10)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("ArrayList<Joke>",this.list)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val context = this
        this.list = savedInstanceState.getSerializable("ArrayList<Joke>") as java.util.ArrayList<Joke>
        setRecycler(list,context)
    }

    /**
     * @desc Adding jokes to the recycler view
     * @param n - Number of jokes to add
     */
    fun addJokes(n : Long) {
        val retrofitData: Observable<Joke> = jokeFactory.giveMeAJoke()
        var loadingProgressBar = findViewById<ProgressBar>(R.id.loading)
        loadingProgressBar.visibility = View.VISIBLE
        var disposable = retrofitData
            .subscribeOn(Schedulers.io())
            .repeat(n)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result -> var joke = result
                adapter?.addItem(joke)
                loadingProgressBar.visibility = View.INVISIBLE
            }


        compositeDisposable?.add(disposable)
    }

    /**
     * @desc Setting the recycler view
     * @param list - List of jokes to put in the recycler view
     * @param context - Context of the activity
     */
    private fun setRecycler(list: java.util.ArrayList<Joke>, context: Context) {
        adapter = JokeAdapter(list,context)
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerViewJokes)

        recyclerview.layoutManager = LinearLayoutManager(this)
        // Création du recycler view
        recyclerview.adapter = adapter
    }

    /**
    * @desc Setting all the listener of the activity
    */
    private fun setListener() {
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewJokes)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    addJokes(10)
                }
            }
        })
    }
}





