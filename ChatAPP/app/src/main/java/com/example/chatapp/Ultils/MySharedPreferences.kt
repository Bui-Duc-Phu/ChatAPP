package com.example.chatapp.Ultils

import android.content.Context

object MySharedPreferences {
    private const val PREF_NAME = "MyPrefs"

    fun putStringValue(context: Context, key: String, value: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(key, value).apply()
    }

    fun getStringValue(context: Context, key: String): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(key, null)
    }
}