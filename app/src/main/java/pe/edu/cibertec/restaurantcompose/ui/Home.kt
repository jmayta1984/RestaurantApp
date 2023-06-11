package pe.edu.cibertec.restaurantcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.restaurantcompose.ui.login.Login
import pe.edu.cibertec.restaurantcompose.ui.restaurants.RestaurantList
import pe.edu.cibertec.restaurantcompose.ui.signup.SignUp

@Composable
fun Home(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login" ){

        composable("register"){
            SignUp(navController)
        }

        composable(("restaurants")){
            RestaurantList(navController)
        }

        composable("login"){
            Login(navController)
        }

    }
}