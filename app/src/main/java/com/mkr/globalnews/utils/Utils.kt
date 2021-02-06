package com.mkr.globalnews.utils

import android.content.Context
import android.util.Log
import java.io.IOException
import java.io.InputStream

object Utils {

    fun getAssetJsonData(context: Context): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = context.assets.open("mock_response.json")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        Log.e("data", json ?: "")
        return json
    }
}