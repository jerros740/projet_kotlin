package com.example.projet_kotlin

import android.R
import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text


class JokeView @JvmOverloads constructor(context: Context) : ConstraintLayout(context) {
    init {
        inflate(getContext(),com.example.projet_kotlin.R.layout.joke_layout, this)
    }

    data class Model(val btnStar : Button, val btnShare : Button, val txtJoke : TextView)
}