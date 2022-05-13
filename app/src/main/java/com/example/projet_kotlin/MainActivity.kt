package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
/**
 * This is our main activity.
 */
class MainActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null
    private var adapter: JokeAdapter? = null
    private var jokeFactory = JokeApiServiceFactory().getJokeApiService()
    private var jokesList:java.util.ArrayList<Joke> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loadingProgressBar = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        loadingProgressBar.isRefreshing = false
        setListener()

        if (savedInstanceState == null) {
            val context = this

            // Retrieve saved jokes
            this.jokesList = JokeMemory().retrieveAll(context)

            setRecycler(this.jokesList,context)
            addJokes(10)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("ArrayList<Joke>",this.jokesList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val context = this
        this.jokesList = savedInstanceState.getSerializable("ArrayList<Joke>") as java.util.ArrayList<Joke>
        setRecycler(this.jokesList,context)
    }

    /**
     * @desc Adding jokes to the recycler view
     * @param n - Number of jokes to add
     */
    fun addJokes(n : Long) {
        val retrofitData: Observable<Joke> = jokeFactory.giveMeAJoke()
        var loadingProgressBar = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        loadingProgressBar.isRefreshing = true
        var disposable = retrofitData
            .subscribeOn(Schedulers.io())
            .repeat(n)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result -> var joke = result
                adapter?.addItem(joke)
                loadingProgressBar.isRefreshing = false
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

        // Creation of recycler view
        recyclerview.adapter = adapter

        // Adding features : moving & swiping
        val itemTouchHelper = JokeTouchHelper(adapter!!)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    /**
    * @desc Setting all the listener of the activity
    */
    private fun setListener() {
        var refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        refreshLayout.setColorSchemeResources(R.color.dark_purple)
        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = true
            adapter?.replaceAll(JokeMemory().retrieveAll(this))
            addJokes(10)
            refreshLayout.isRefreshing = false
        }

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewJokes)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    refreshLayout.isRefreshing = true
                    addJokes(10)
                    refreshLayout.isRefreshing = false
                }
            }
        })
    }
}





