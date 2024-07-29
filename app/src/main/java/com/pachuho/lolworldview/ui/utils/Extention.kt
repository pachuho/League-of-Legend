package com.pachuho.lolworldview.ui.utils

import android.content.Context
import android.widget.Toast
import com.pachuho.lolworldview.ui.utils.MoshiProvider.moshi
import com.squareup.moshi.Types

fun Context.showToast(message: String?) {
    message?.let {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

inline fun <reified T> T.serialize(): String? {
    val adapter = moshi.adapter(T::class.java)
    return adapter.toJson(this)
}

inline fun <reified T> String.deserialize(): T? {
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(this)
}

inline fun <reified T> List<T>.serializeList(): String? {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter = moshi.adapter<List<T>>(type)
    return adapter.toJson(this)
}

inline fun <reified T> String.deserializeList(): List<T>? {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter = moshi.adapter<List<T>>(type)
    return adapter.fromJson(this)
}