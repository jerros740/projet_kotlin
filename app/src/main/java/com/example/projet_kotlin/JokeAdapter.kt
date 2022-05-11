package com.example.projet_kotlin

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageButton
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

    class ViewHolder(view: JokeView) : RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.txtViewJoke)

        init {
            val btnShare: ImageButton = view.findViewById(R.id.btnShare)
            val btnStar: ImageButton = view.findViewById(R.id.btnStar)
            var on: Boolean = false
            btnShare.setOnClickListener { view ->
                Log.d(TAG, "Share")
                Log.d(TAG, textView.text.toString())
            }
            btnStar.setOnClickListener { view ->
                Log.d(TAG, "Star")
                on = if(!on) {
                    btnStar.setImageResource(android.R.drawable.btn_star_big_on)
                    true
                } else {
                    btnStar.setImageResource(android.R.drawable.btn_star_big_off)
                    false
                }
            }
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

    /**
     * @desc Remove item to the recycler view
     * @param position - Remove the joke on a particular position
     */
    fun removeItem(position : Int)
    {
        listOfJoke.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listOfJoke.size)
    }
}