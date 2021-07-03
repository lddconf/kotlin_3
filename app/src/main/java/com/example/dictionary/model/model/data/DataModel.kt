package com.example.dictionary.model.model.data

import com.google.gson.annotations.SerializedName

data class DataModel(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
