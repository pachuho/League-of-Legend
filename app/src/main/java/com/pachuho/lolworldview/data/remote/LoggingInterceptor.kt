package com.pachuho.lolworldview.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(TAG, "====================================================")
        Log.d(TAG, "request : $request")
        val response = chain.proceed(request)
        Log.d(TAG,"response : $response")
        Log.d(TAG, "====================================================")
        return response
    }

    companion object {
        val TAG: String = this::class.java.name
    }
}