package pe.edu.cibertec.restaurantcompose.data.remote

import pe.edu.cibertec.restaurantcompose.data.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantInterface {
    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurant>>

}