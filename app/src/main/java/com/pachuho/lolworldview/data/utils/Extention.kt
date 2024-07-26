package com.pachuho.lolworldview.data.utils

import com.pachuho.lolworldview.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <T, R> Response<T>.fetch(
    crossinline transform: (T) -> R
): Flow<UiState<R>> = flow {
    emit(UiState.Loading)

    if (isSuccessful) {
        val body = body()
        if (body != null) {
            emit(UiState.Success(transform(body)))
        } else {
            throw EmptyBodyException("[${code()}] - ${raw()}")
        }
    } else {
        throw NetworkFailureException("[${code()}] - ${raw()}")
    }
}.catch { emit(UiState.Error(it)) }