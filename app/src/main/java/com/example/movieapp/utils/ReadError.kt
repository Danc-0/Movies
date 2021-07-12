package com.example.movieapp.utils

import okhttp3.ResponseBody
import org.json.JSONObject

class ReadError {
    fun readError(errorBody: ResponseBody?): String {
        val jsonObj = JSONObject(errorBody!!.charStream().readText())
        return try {

            return jsonObj.getString("status_message")

        } catch (_: Exception) {
            "There was an error please try again"
        }

    }
}