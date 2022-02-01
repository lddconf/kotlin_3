package com.example.dictionary.ui.adapters.helpers

import com.example.dictionary.ui.adapters.ResultListRVAdapter

interface IWordDetails {
    var pos: Int
    fun headerText(text: String)
    fun descriptionText(text: String)
}