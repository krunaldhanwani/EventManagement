package com.example.eventapp.utils

import android.content.Context

class MyPreferences {

    companion object {
        fun saveStringInPreference(context: Context, key: String, value: String) {
            val preferences = context.getSharedPreferences("Event", Context.MODE_PRIVATE)
            preferences.edit().putString(key, value).apply()
        }

        fun saveIntInPreference(context: Context, key: String, value: Int) {
            val preferences = context.getSharedPreferences("Event", Context.MODE_PRIVATE)
            preferences.edit().putInt(key, value).apply()
        }

        fun saveBooleanInPreference(context: Context, key: String, value: Boolean) {
            val preferences = context.getSharedPreferences("Event", Context.MODE_PRIVATE)
            preferences.edit().putBoolean(key, value).apply()
        }

        fun getFromPreferences(context: Context, key: String): String? {
            return context.getSharedPreferences("Event", Context.MODE_PRIVATE).getString(key, "")
        }

        fun getFcmToken(context: Context): String {
            return getFromPreferences(context, "fcm") ?: ""
        }

        fun saveFcmToken(context: Context, token: String) {
            saveStringInPreference(context, "fcm", token)
        }
    }
}