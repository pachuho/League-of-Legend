package com.pachuho.lolworldview.data.utils

import com.pachuho.lolworldview.data.model.ChampionResponse
import com.pachuho.lolworldview.ui.utils.UiState
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response

suspend fun <T> FlowCollector<UiState<T>>.fetch(response: Response<ChampionResponse<T>>) {
    if (response.isSuccessful) {
        response.body()?.let {
            emit(UiState.Success(it.toList().first()))
        } ?: run {
            throw EmptyBodyException(getException(response))
        }
    } else {
        throw NetworkFailureException(getException(response))
    }
}

suspend fun <T> FlowCollector<UiState<List<T>>>.fetchList(response: Response<ChampionResponse<T>>) {
    if (response.isSuccessful) {
        response.body()?.let {
            emit(UiState.Success(it.toList()))
        } ?: run {
            throw EmptyBodyException(getException(response))
        }
    } else {
        throw NetworkFailureException(getException(response))
    }
}

fun<T> getException(response: Response<T>) =
    "code: ${response.code()}\nmessage: ${response.raw()}"