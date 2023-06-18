package pe.edu.cibertec.restaurantcompose.data.repository

import pe.edu.cibertec.restaurantcompose.data.model.Restaurant
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import pe.edu.cibertec.restaurantcompose.data.remote.service.RestaurantService
import pe.edu.cibertec.restaurantcompose.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantRepository(
    private val restaurantService: RestaurantService = ApiClient.getRestaurantInterface()

) {
    fun getRestaurants(callback: (Result<List<Restaurant>>) -> Unit) {

        restaurantService.getRestaurants().enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                if (response.body() == null) {
                    callback(Result.Error("No data found"))
                    return
                }
                callback(Result.Success(response.body()!!))
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                callback(Result.Error("Get restaurants not available"))
            }
        })
    }

}