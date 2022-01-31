package com.example.dictionary.ui.navigation

import com.example.dictionary.ui.fragments.WordsDetailsFragment
import com.example.dictionary.model.navigation.IDictionaryAppScreens
import com.example.dictionary.ui.fragments.SettingsFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidAppScreens : IDictionaryAppScreens {
    override fun searchResultsWindow(): Screen =
        FragmentScreen {
            WordsDetailsFragment.newInstance()
        }

    override fun settingsWindow(): Screen =
        FragmentScreen {
            SettingsFragment.newInstance()
    }
}