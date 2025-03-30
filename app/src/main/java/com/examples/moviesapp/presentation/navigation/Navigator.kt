package com.examples.moviesapp.presentation.navigation

import androidx.navigation.NavController
import com.examples.moviesapp.R

class Navigator : MovieRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    private fun canNavigate(startDestination: Int, endDestination: Int)
            = navController?.currentDestination?.id == startDestination
            && navController?.currentDestination?.id != endDestination

    override fun navigateToRoot(destinationId: Int) {
        navController?.let {
            it.popBackStack(it.graph.startDestinationId, false)
            if (it.currentDestination?.id != destinationId) {
                it.navigate(destinationId)
            }
        }
    }

    override fun navigateToFilm() {
        if (!canNavigate(R.id.nav_home, R.id.filmFragment))
            return
        navController?.navigate(R.id.action_nav_home_to_filmFragment)
    }

    override fun clickButtonBack() {
        navController?.popBackStack()
    }
}