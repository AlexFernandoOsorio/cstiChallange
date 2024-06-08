package com.example.cstichallenge.core.utils

import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.cstichallange.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")

    const val END_POINT_LOGIN = BuildConfig.END_POINT_LOGIN
    const val END_POINT_REGISTER = BuildConfig.END_POINT_REGISTER
    const val END_POINT_RESOURCE = BuildConfig.END_POINT_RESOURCE
}