package com.example.dictionary.mvp.model.data

sealed class ScreenData

data class Success<T>(val data: T?) : ScreenData()
data class InProgress<T>(val progress: Int?) : ScreenData()
data class Error<T>(val exception: Throwable) : ScreenData()



