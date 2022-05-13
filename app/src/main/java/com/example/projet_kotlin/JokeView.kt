package com.example.projet_kotlin

import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * This is our class where we will initialize and make change to our view (Joke + buttons(star/share))
 */
class JokeView constructor(context: Context) : ConstraintLayout(context) {

    // Boolean to know if star button is on/off (can be changed if joke is already saved)
    var on: Boolean = false

    init {
        inflate(context, R.layout.joke_layout, this)
        initButtonListener()
    }

    /**
     * @desc Data class for all the view component
     * @param joke - Joke object
     * @param imgBtnStar - value for the image ressource of the star button
     * @param imgBtnShare - value for the image ressource of the share button
     */
    data class Model(val joke: Joke, val imgBtnStar: Int, val imgBtnShare: Int)

    /**
     * @desc Changing model of view component
     * @param model - New model for the all the components (text, image button)
     */
    fun setupView(model : Model)
    {
        val txtViewJoke:TextView = findViewById(R.id.txtViewJoke)
        val btnShare: ImageButton = findViewById(R.id.btnShare)
        val btnStar: ImageButton = findViewById(R.id.btnStar)

        val joke = model.joke
        val drawableShare = model.imgBtnShare
        val drawableStar = model.imgBtnStar

        txtViewJoke.text = joke.value
        btnShare.setImageResource(drawableShare)
        btnStar.setImageResource(drawableStar)
    }

    /**
     * @desc Initialize all button listener
     */
    fun initButtonListener() {
        val jokeMemory = JokeMemory()
        val txtViewJoke:TextView = findViewById(R.id.txtViewJoke)

        // Handling event for sharing button
        val btnShare: ImageButton = findViewById(R.id.btnShare)
        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, txtViewJoke.text)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Partager")
            context.startActivity(shareIntent)
        }

        // Handling event for star button
        val btnStar: ImageButton = findViewById(R.id.btnStar)

        btnStar.setOnClickListener {
            val value : Joke = txtViewJoke.tag as Joke
            val key : String = value.id

            on = if(!on) {
                btnStar.setImageResource(android.R.drawable.btn_star_big_on)
                jokeMemory.save(context,key,value)
                true
            } else {
                btnStar.setImageResource(android.R.drawable.btn_star_big_off)
                jokeMemory.delete(context,key)
                false
            }
        }
    }

}