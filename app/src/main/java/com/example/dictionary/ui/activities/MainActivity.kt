package com.example.dictionary.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
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

    companion object {
        fun getInstanceIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.router.replaceScreen(AndroidAppScreens().mainWindow())

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