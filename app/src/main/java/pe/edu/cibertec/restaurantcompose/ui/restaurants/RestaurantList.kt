package pe.edu.cibertec.restaurantcompose.ui.restaurants

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.cibertec.restaurantcompose.data.model.Restaurant
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import pe.edu.cibertec.restaurantcompose.data.repository.RestaurantRepository
import pe.edu.cibertec.restaurantcompose.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RestaurantList(navController: NavController) {

    val restaurants = remember {
        mutableStateOf(listOf<Restaurant>())
    }

    val restaurantRepository = RestaurantRepository()
    val context = LocalContext.current

    restaurantRepository.getRestaurants { result ->
        if (result is Result.Success ){
            restaurants.value = result.data!!
        } else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn {
        items(restaurants.value) { restaurant ->

            Box(modifier = Modifier.padding(8.dp)) {
                Card {
                    AsyncImage(
                        model = restaurant.posterUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .height(256.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(text = restaurant.title, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

}