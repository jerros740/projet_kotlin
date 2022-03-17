package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val joke1: Joke = Joke("1")
        val joke2: Joke = Joke("2")
        val joke3: Joke = Joke("3")
        val joke4: Joke = Joke("4")
        val joke5: Joke = Joke("5")
        val joke6: Joke = Joke("6")
        val joke7: Joke = Joke("7")
        var context: Context = this;
        initRecycler(listOf(joke1,joke2,joke3,joke4),context)
    }

    fun initRecycler(list: List<Joke>, context: Context)
    {
        var adapter = JokeAdapter(list,context)
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerViewJokes)
        // this creates a vertical layout Manage
        recyclerview.layoutManager = LinearLayoutManager(this)
        // Création du recycler view
        recyclerview.adapter = adapter
    }
}