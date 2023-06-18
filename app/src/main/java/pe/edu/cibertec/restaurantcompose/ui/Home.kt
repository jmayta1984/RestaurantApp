package pe.edu.cibertec.restaurantcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.restaurantcompose.ui.login.Login
import pe.edu.cibertec.restaurantcompose.ui.restaurants.RestaurantList
import pe.edu.cibertec.restaurantcompose.ui.signup.SignUp

@Composable
fun Home() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Login.route) {

        composable(Route.SignUp.route) {
            SignUp(navController)
        }

        composable(Route.Restaurants.route) {
            RestaurantList(navController)
        }

        composable(Route.Login.route) {
            Login(navController)
        }

    }
}

sealed class Route(val route: String) {
    object Login : Route("login")
    object Restaurants : Route("restaurants")
    object SignUp : Route("sign_up")
}