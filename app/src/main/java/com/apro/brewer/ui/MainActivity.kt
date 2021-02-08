package com.apro.brewer.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.apro.brewer.DI.appComponent
import com.apro.brewer.R
import com.apro.brewer.databinding.ActivityMainBinding
import com.apro.brewer.navigation.AppNavigator
import com.apro.brewer.ui.screens.Screens

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(
        this,
        R.id.mainContainerView,
        supportFragmentManager,
        supportFragmentManager.fragmentFactory
    )

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        appComponent.appRouter().newRootScreen(Screens.main())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        appComponent.navigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        appComponent.navigatorHolder().removeNavigator()
        super.onPause()
    }
}