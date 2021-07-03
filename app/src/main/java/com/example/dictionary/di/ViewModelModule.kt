package geekbrains.ru.translator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.ui.viewmodel.WordsDetailsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordsDetailsFragmentViewModel::class)
    protected abstract fun wordsDetailsViewModel(wordsDetailsViewModel: WordsDetailsFragmentViewModel): ViewModel
}
