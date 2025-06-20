package com.examples.moviesapp.presentation.navigation

import androidx.navigation.NavController
import com.examples.moviesapp.R
import com.examples.moviesapp.presentation.screens.actor.ActorFragment
import com.examples.moviesapp.presentation.screens.film.FilmFragment

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

    override fun navigateToFilm(filmId: Int) {
        if (!canNavigate(R.id.nav_home, R.id.filmFragment))
            return
        navController?.navigate(R.id.action_nav_home_to_filmFragment, FilmFragment.createBundle(filmId))
    }

    override fun navigateToActor(actorId: Int) {
        if (!canNavigate(R.id.filmFragment, R.id.actorFragment))
            return
        navController?.navigate(R.id.action_filmFragment_to_actorFragment, ActorFragment.createBundle(actorId))
    }

    override fun clickButtonBack() {
        navController?.popBackStack()
    }
}