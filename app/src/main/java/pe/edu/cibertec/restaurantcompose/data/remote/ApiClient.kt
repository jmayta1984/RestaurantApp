package pe.edu.cibertec.restaurantcompose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL = "https://plain-marbled-muskox.glitch.me/"
    private var restaurantInterface: RestaurantInterface? = null
    private var userInterface: UserInterface? = null

    fun getRestaurantInterface(): RestaurantInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        restaurantInterface = retrofit.create(RestaurantInterface::class.java)

        return restaurantInterface as RestaurantInterface
    }

    fun getUserInterface(): UserInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userInterface = retrofit.create(UserInterface::class.java)

        return userInterface as UserInterface
    }
}