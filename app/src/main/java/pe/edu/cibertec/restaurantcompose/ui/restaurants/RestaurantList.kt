package pe.edu.cibertec.restaurantcompose.ui.restaurants

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import pe.edu.cibertec.restaurantcompose.data.model.Restaurant

@Composable
fun RestaurantList() {

    val restaurants = remember {
        mutableStateOf(listOf<Restaurant>())
    }

    LazyColumn(){
        items(restaurants.value) {
            Text(text = it.title)
        }
    }

}