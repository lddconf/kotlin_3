package com.example.dictionary.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.App
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.ui.navigation.AndroidAppScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navigationHolder: NavigatorHolder = App.instance.navigatorHolder
    private val navigator = AppNavigator(this, R.id.container)
    private val screens = AndroidAppScreens()

    companion object {
        fun getInstanceIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBaseView()
    }

    fun initBaseView() {
        App.instance.router.replaceScreen(screens.searchResultsWindow())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}