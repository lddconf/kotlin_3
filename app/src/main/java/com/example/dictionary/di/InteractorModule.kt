package com.example.dictionary.di

import com.example.dictionary.model.interactors.SettingsInteractor
import com.example.dictionary.model.interactors.WordsDetailsInteractor
import com.example.dictionary.model.model.data.DataModel
import com.example.dictionary.model.model.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = WordsDetailsInteractor(repositoryRemote, repositoryLocal)

    @Provides
    internal fun provideSettingsInteractor() = SettingsInteractor()

}
