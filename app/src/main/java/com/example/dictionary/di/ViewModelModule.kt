package com.example.dictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.ui.viewmodel.MainViewModel
import com.example.dictionary.ui.viewmodel.SettingsViewModel
import com.example.dictionary.ui.viewmodel.WordsDetailsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import geekbrains.ru.translator.di.ViewModelFactory
import geekbrains.ru.translator.di.ViewModelKey

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordsDetailsFragmentViewModel::class)
    protected abstract fun wordsDetailsViewModel(wordsDetailsViewModel: WordsDetailsFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun settingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}
