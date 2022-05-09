package com.example.projet_kotlin

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView


class JokeAdapter(jokes :List<Joke>, context: Context) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    private var context: Context
    var listOfJoke = mutableListOf<Joke>()

    init
    {
        this.listOfJoke = jokes as MutableList<Joke>
        this.context = context
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.textView)

        init {
            val btnShare: Button = view.findViewById(R.id.btnShare)
            val btnStar: Button = view.findViewById(R.id.btnStar)
            btnShare.setOnClickListener(View.OnClickListener { view ->
                Log.d(TAG, "Share")
            })
            btnStar.setOnClickListener(View.OnClickListener { view ->
                Log.d(TAG, "Star")
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val jokeView = JokeView(this.context)

        return ViewHolder(jokeView)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.textView.text = listOfJoke[position].value
    }

    override fun getItemCount(): Int {
        return listOfJoke.size
    }

    /**
     * @desc Add item to the recycler view
     * @param joke - Joke to add to the recycler view
     */
    fun addItem(joke : Joke)
    {
        listOfJoke.add(joke)
        this.notifyItemInserted(getItemCount() - 1);
    }
}