package com.example.dictionary.di

import com.example.dictionary.ui.fragments.BottomSearchDialogFragment
import com.example.dictionary.ui.fragments.SettingsFragment
import com.example.dictionary.ui.fragments.WordsDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeWordsDetailsFragment(): WordsDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

}
