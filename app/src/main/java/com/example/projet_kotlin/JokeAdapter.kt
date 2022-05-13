package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter pattern used for the recycler view
 */
class JokeAdapter(jokes :List<Joke>, private var context: Context) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    private val jokeMemory : JokeMemory = JokeMemory()
    private var favoritesJokeList = jokeMemory.retrieveAll(context)
    var jokeList = jokes as MutableList<Joke>

    class ViewHolder(view: JokeView) : RecyclerView.ViewHolder(view) {
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

        val drawableStarOn : Int = android.R.drawable.btn_star_big_on
        val drawableStarOff : Int = android.R.drawable.btn_star_big_off
        val drawableShare : Int = android.R.drawable.ic_menu_share

        if (holder.textView.tag as Joke in favoritesJokeList) {
            holder.jokeView.setupView(JokeView.Model(jokeList[position],drawableStarOn,drawableShare))
            holder.jokeView.on = true
        }
        else {
            holder.jokeView.setupView(JokeView.Model(jokeList[position],drawableStarOff,drawableShare))
        }
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    /**
     * @desc Add item to the recycler view
     * @param joke - Joke to add to the recycler view
     */
    fun addItem(joke : Joke) {
        jokeList.add(joke)
        this.notifyItemInserted(getItemCount() - 1)
    }

    /**
     * @desc Remove item to the recycler view
     * @param position - Remove the joke on a particular position
     */
    fun removeItem(position : Int) {
        jokeList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, jokeList.size)
    }

    /**
     * @desc Replace all our joke with new jokes
     * @param pJokeList - Remove the joke on a particular position
     */
    fun replaceAll(pJokeList : ArrayList<Joke>) {
        this.favoritesJokeList = jokeMemory.retrieveAll(context)
        this.jokeList = pJokeList
        notifyDataSetChanged()
    }
}