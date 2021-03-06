package com.example.projet_kotlin

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
/**
 * This is our class to handle what we will do when we move(up/dowb) or swipe(left/right)
 */
class JokeTouchHelper(adapter: JokeAdapter) : ItemTouchHelper(
    object : ItemTouchHelper.SimpleCallback(
        UP or DOWN,
        LEFT or RIGHT
    ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) : Boolean
        {
            val list: MutableList<Joke> = adapter.jokeList

            val from = viewHolder.adapterPosition
            val to = target.adapterPosition

            val tmp = list[from]
            list[from] = list[to]
            list[to] = tmp

            adapter.notifyItemMoved(from,to)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            adapter.removeItem(position)
        }
    }
)