package com.openapi.task.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.openapi.task.data.remote.SharedPrefConstants

class PrefRepository(val context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(SharedPrefConstants.MAIN_SHARED, Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

     fun putSharedPreferencesObject(key: String?, shared_object: Any?) {
        val json = gson.toJson(shared_object)
        editor.putString(key, json)
        editor.commit()
    }

    fun getSharedPreferencesObject(
        key: String?,
        neededClass: Class<*>?
    ): Any? {
        val json = pref.getString(key, "")
        return gson.fromJson<Any>(json, neededClass)
    }

}