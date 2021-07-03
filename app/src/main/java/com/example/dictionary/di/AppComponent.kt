package geekbrains.ru.translator.di

import com.example.dictionary.App
import com.example.dictionary.di.AppModule
import com.example.dictionary.di.CiceroneModule
import com.example.dictionary.ui.activities.MainActivity
import com.example.dictionary.ui.fragments.WordsDetailsFragment
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent {
    fun inject(app: App)

    fun inject(mainActivity: MainActivity)
    fun inject(wordsDetailsFragment: WordsDetailsFragment)

}
