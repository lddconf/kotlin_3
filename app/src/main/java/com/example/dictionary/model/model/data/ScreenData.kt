package com.example.dictionary.model.model.data

sealed class ScreenData

data class SSuccess<T>(val data: T?) : ScreenData()
data class SInProgress(val progress: Int?) : ScreenData()
data class SError(val exception: Throwable) : ScreenData()
object SComplete :ScreenData()