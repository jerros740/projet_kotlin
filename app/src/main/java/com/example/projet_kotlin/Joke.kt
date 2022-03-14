package com.example.projet_kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@RequiresApi(Build.VERSION_CODES.N)
class Joke()
{
    private val url = "https://api.chucknorris.io/jokes/random"
    private var jsonJoke = JSONObject()
    init
    {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun init()
    {
        val thread = Thread {
            try {
                val url = URL(url)

                with(url.openConnection() as HttpURLConnection)
                {
                    requestMethod = "GET"

                    var str = ""
                    inputStream.bufferedReader().use {
                        it.lines().forEach { line ->
                            str += line
                        }
                    }
                    // Conversion de la string reçu en JSON
                    jsonJoke = JSONObject(str)
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun icon(): Any
    {
        return jsonJoke.get("icon_url")
    }

    fun url(): Any
    {
        return jsonJoke.get("url")
    }

    fun value(): Any
    {
        return jsonJoke.get("value")
    }
}