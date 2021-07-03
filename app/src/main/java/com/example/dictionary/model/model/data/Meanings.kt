package com.example.dictionary.model.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)
