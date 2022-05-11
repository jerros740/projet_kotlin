package com.example.projet_kotlin

import android.content.Context
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text


class JokeView constructor(context: Context) : ConstraintLayout(context) {

    init {
        inflate(getContext(), R.layout.joke_layout, this)

    }

    data class Model(val txtJoke: String = "", val imgBtnShare: Int = -1, val imgBtnStar: Int = -1)

    /*
    fun setupView(model : Model)
    {
        val txtViewJoke:TextView = findViewById(R.id.txtViewJoke)
        val btnShare: ImageButton = findViewById(R.id.btnShare)
        val btnStar: ImageButton = findViewById(R.id.btnStar)

        var txtJoke = model.txtJoke
        var drawableShare = model.imgBtnShare
        var drawableStar = model.imgBtnStar

        if(txtJoke != "")
            txtViewJoke.setText(txtJoke)
        if(drawableShare != -1)
            btnShare.setImageResource(drawableShare)
        if(drawableStar != -1)
            btnStar.setImageResource(drawableStar)
    }*/
}