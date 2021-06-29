package com.example.dictionary.ui.navigation

import com.example.dictionary.ui.fragments.WordsDetailsFragment
import com.example.dictionary.mvp.navigation.IDictionaryAppScreens
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidAppScreens : IDictionaryAppScreens {
    override fun searchResultsWindow(): Screen =
        FragmentScreen {
            WordsDetailsFragment.newInstance()
        }
}