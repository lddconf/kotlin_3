package com.example.dictionary.mvp.data

import com.google.gson.annotations.SerializedName
import geekbrains.ru.translator.model.data.Meanings

data class DataModel(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
