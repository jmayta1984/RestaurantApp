package pe.edu.cibertec.restaurantcompose.ui.restaurants

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import pe.edu.cibertec.restaurantcompose.data.model.Restaurant
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RestaurantList() {

    val restaurants = remember {
        mutableStateOf(listOf<Restaurant>())
    }

    val restaurantInterface = ApiClient.getRestaurantInterface()
    val getRestaurants = restaurantInterface.getRestaurants()

    getRestaurants.enqueue(object : Callback<List<Restaurant>> {
        override fun onResponse(
            call: Call<List<Restaurant>>,
            response: Response<List<Restaurant>>
        ) {
            if (response.isSuccessful) {
                if ( response.body() == null) {
                    restaurants.value = emptyList()
                } else {
                    restaurants.value =response.body()!!
                }
            }
        }

        override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
            t.message?.let { Log.d("RestaurantList", it) }
        }
    })

    LazyColumn() {
        items(restaurants.value) {
            Text(text = it.title)
        }
    }

}