package pe.edu.cibertec.restaurantcompose.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.cibertec.restaurantcompose.data.model.User
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import pe.edu.cibertec.restaurantcompose.ui.theme.RestaurantComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Username") },
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.Person, null) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Password") },
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.Lock, null) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            onClick = {
                val userInterface = ApiClient.getUserInterface()

                val deleteUser = userInterface.deleteUser(13)

                deleteUser.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {

                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        t.message?.let { Log.d("RestaurantList", it) }

                    }

                })
            }) {
            Text(text = "Sign in")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            onClick = {
                val userInterface = ApiClient.getUserInterface()

                val createUser = userInterface.createUser(User("Prueba", "Password"))

                createUser.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {

                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        t.message?.let { Log.d("RestaurantList", it) }

                    }

                })

            }) {
            Text(text = "Sign up")
        }
        TextButton(
            onClick = { /*TODO*/ }) {
            Text(text = "Forgot password")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    RestaurantComposeTheme {
        Login()
    }
}
