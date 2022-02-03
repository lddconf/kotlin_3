package com.example.dictionary.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.model.model.data.*
import com.example.dictionary.model.model.data.settings.SettingsHolder
import com.example.dictionary.model.navigation.IDictionaryAppScreens
import com.example.dictionary.ui.base.BaseActivity
import com.example.dictionary.ui.viewmodel.MainViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<AppScreens, MainViewModel>() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: MainViewModel

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var screens: IDictionaryAppScreens

    @Inject
    lateinit var router: Router

    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAppBar()


        viewModel = viewModelFactory.create(MainViewModel::class.java)
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
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

    private fun initAppBar() {
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        R.id.action_settings -> {
            viewModel.loadSettings()
            true
        }
        else -> {
            super.onOptionsItemSelected(item);
            false
        }
    }

    override fun renderData(data: AppScreens) = when (data) {
        is SettingsWindow -> navigateToSettings()
        is DictionaryWindow -> showDictionaryBaseWindow()
    }

    private fun navigateToSettings() {
       // hideMainAppBar()
        router.navigateTo(screens.settingsWindow())
    }

    private fun showDictionaryBaseWindow() {
       // showMainAppBar()
        router.replaceScreen(screens.searchResultsWindow())
    }

    private fun hideMainAppBar() {
        binding.appBarLayoutId.visibility = View.GONE
    }

    private fun showMainAppBar() {
        binding.appBarLayoutId.visibility = View.VISIBLE
    }
}