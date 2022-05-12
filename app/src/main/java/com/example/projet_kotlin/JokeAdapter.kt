package com.example.projet_kotlin

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

/**
 * Adapter pattern used for the recycler view
 */
class JokeAdapter(jokes :List<Joke>, context: Context) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    private var context: Context = context
    private val jokeMemory : JokeMemory = JokeMemory()
    private val favoritesJokeList = jokeMemory.retrieveAll(context)
    var jokeList = jokes as MutableList<Joke>

    class ViewHolder(view: JokeView) : RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.txtViewJoke)
        val jokeView: JokeView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val jokeView = JokeView(this.context)

        return ViewHolder(jokeView)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.tag = jokeList[position]

        val drawableStar_on : Int = android.R.drawable.btn_star_big_on
        val drawableStar_off : Int = android.R.drawable.btn_star_big_off
        val drawableShare : Int = android.R.drawable.ic_menu_share

        if (holder.textView.tag as Joke in favoritesJokeList) {
            holder.jokeView.setupView(JokeView.Model(jokeList[position],drawableStar_on,drawableShare))
            holder.jokeView.on = true
        }
        else {
            holder.jokeView.setupView(JokeView.Model(jokeList[position],drawableStar_off,drawableShare))
        }
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    /**
     * @desc Add item to the recycler view
     * @param joke - Joke to add to the recycler view
     */
    fun addItem(joke : Joke)
    {
        jokeList.add(joke)
        this.notifyItemInserted(getItemCount() - 1);
    }

    /**
     * @desc Remove item to the recycler view
     * @param position - Remove the joke on a particular position
     */
    fun removeItem(position : Int)
    {
        jokeList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, jokeList.size)
    }
}