package pe.edu.cibertec.restaurantcompose.data.remote

import pe.edu.cibertec.restaurantcompose.data.remote.service.RestaurantService
import pe.edu.cibertec.restaurantcompose.data.remote.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL = "https://plain-marbled-muskox.glitch.me/"

    private var retrofit: Retrofit? = null
    private var restaurantService: RestaurantService? = null
    private var userService: UserService? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

    fun getRestaurantInterface(): RestaurantService {
        if (restaurantService == null) {
            restaurantService = getRetrofit().create(RestaurantService::class.java)
        }
        return restaurantService as RestaurantService
    }

    fun getUserInterface(): UserService {
        if (userService == null){
            userService = getRetrofit().create(UserService::class.java)
        }
        return userService as UserService
    }
}