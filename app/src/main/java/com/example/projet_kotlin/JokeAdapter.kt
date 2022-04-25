package com.example.projet_kotlin

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter(jokes :List<Joke>, context: Context) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    private var context: Context? = null
    var listOfJoke = listOf<Joke>()
    init
    {
        this.listOfJoke = jokes
        this.context = context
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.textView)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(this.context)
        val view = inflater.inflate(R.layout.test, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.textView.text = listOfJoke[position].value
    }

    override fun getItemCount(): Int {
        return listOfJoke.size
    }

}