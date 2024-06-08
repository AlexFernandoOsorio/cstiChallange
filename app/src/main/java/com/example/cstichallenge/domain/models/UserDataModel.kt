package com.example.cstichallenge.domain.models

import com.google.gson.Gson

data class UserDataModel(
    var id: Int? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var avatar: String? = null
){
    fun toJson(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun fromJson(json: String): UserDataModel {
            val gson = Gson()
            return gson.fromJson(json, UserDataModel::class.java)
        }
    }
}
