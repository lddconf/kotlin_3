package com.example.dictionary.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.App
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.model.model.data.SComplete
import com.example.dictionary.model.model.data.ScreenData
import com.example.dictionary.model.navigation.IDictionaryAppScreens
import com.example.dictionary.ui.base.BaseActivity
import com.example.dictionary.ui.navigation.AndroidAppScreens
import com.example.dictionary.ui.viewmodel.MainViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : BaseActivity<ScreenData, MainViewModel>() {
    private lateinit var binding: ActivityMainBinding
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var viewModel: MainViewModel

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var screens : IDictionaryAppScreens

    @Inject
    lateinit var router: Router

    private val navigator = AppNavigator(this, R.id.container)

    companion object {
        fun getInstanceIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModelFactory.create(MainViewModel::class.java)
        viewModel.liveData.observe(this, Observer {
            renderData(it)
        })
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun renderData(data: ScreenData) {
        when(data) {
            is SComplete -> router.navigateTo(screens.searchResultsWindow())
            else -> return
        }
    }
}