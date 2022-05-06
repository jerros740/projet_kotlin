package com.example.projet_kotlin

import android.R
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout


class JokeView @JvmOverloads constructor(context: Context) : ConstraintLayout(context) {
    init {
        inflate(getContext(),com.example.projet_kotlin.R.layout.joke_layout, this)
    }

}