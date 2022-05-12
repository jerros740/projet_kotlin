package com.example.projet_kotlin

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * This is our class enabling us store, delete or retrieve our jokes using shared preferences
 */
class JokeMemory {

    /**
     * @desc Saving a joke using shared preferences
     * @param context - context of the application
     * @param key - unique key(string) to store our value
     * @param joke - joke object to be stored
     */
    fun save(context : Context, key : String, joke : Joke) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("chuck",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(joke)

        editor.putString(key, json);
        editor.apply();
    }

    /**
     * @desc Delete a joke using shared preferences
     * @param context - context of the application
     * @param key - unique key(string) of the joke we want to delete
     */
    fun delete(context : Context, key : String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("chuck",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.remove(key)
        editor.apply()
    }

    /**
     * @desc Getting all the jokes we stored
     * @param context - context of the application
     * @param key - unique key(string) of the joke we want to delete
     * @return ArrayList<Joke>
     */
    fun retrieveAll(context : Context): ArrayList<Joke>{
        val favorites : Map<String, *> = context.getSharedPreferences("chuck",Context.MODE_PRIVATE).all
        val arrayList : ArrayList<Joke> = ArrayList()

        for ((key,value) in favorites.entries) {
            val joke:Joke = Gson().fromJson(value as String,Joke::class.java)
            arrayList.add(joke)
        }

        return arrayList
    }
}