package com.examples.moviesapp.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.ActivityMainBinding
import com.examples.moviesapp.presentation.navigation.Navigator
import com.examples.moviesapp.utils.appComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigator: Navigator
    private var navController: NavController? = null

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inject()

        navController = navHostFragment.navController

        navController?.let {
            navigator.attachNavController(it, R.navigation.nav_graph)
            binding.idBottomNav.setupWithNavController(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.let {
            navigator.detachNavController(it)
        }
        navController = null
    }

    fun inject() {
        appComponent().inject(this)
    }
}