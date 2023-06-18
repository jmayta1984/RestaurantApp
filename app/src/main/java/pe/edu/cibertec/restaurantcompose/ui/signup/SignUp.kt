package pe.edu.cibertec.restaurantcompose.ui.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.restaurantcompose.data.model.User
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import pe.edu.cibertec.restaurantcompose.ui.Route
import pe.edu.cibertec.restaurantcompose.ui.theme.RestaurantComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {

    val username = remember {
        mutableStateOf(TextFieldValue())
    }
    val password = remember {
        mutableStateOf(TextFieldValue())
    }

    val confirmPassword = remember {
        mutableStateOf(TextFieldValue())
    }

    val showPassword = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = {
                username.value = it
            },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            onValueChange = {
                password.value = it
            },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword.value = !showPassword.value
                    }) {
                    if (!showPassword.value) {
                        Icon(Icons.Default.Visibility, null)
                    } else {
                        Icon(Icons.Default.VisibilityOff, null)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),

            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Confirm password") },
            value = confirmPassword.value,
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            onValueChange = {
                confirmPassword.value = it
            },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword.value = !showPassword.value
                    }) {
                    if (!showPassword.value) {
                        Icon(Icons.Default.Visibility, null)
                    } else {
                        Icon(Icons.Default.VisibilityOff, null)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),

            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            onClick = {
                val userInterface = ApiClient.getUserInterface()

                val validateUser = userInterface.validateUser(username.value.text)

                validateUser.enqueue(object: Callback<List<User>>{
                    override fun onResponse(
                        call: Call<List<User>>,
                        response: Response<List<User>>
                    ) {
                        if (response.isSuccessful){
                            Log.d("SignUp", response.body()!!.toString())
                            if (response.body()!!.isEmpty()){
                                Toast.makeText(context, "Nuevo usuario", Toast.LENGTH_SHORT).show()
                                val createUser =
                                    userInterface.createUser(User(username.value.text, password.value.text))

                                createUser.enqueue(object : Callback<User> {
                                    override fun onResponse(call: Call<User>, response: Response<User>) {
                                        if (response.isSuccessful) {
                                            navController.navigate(Route.Restaurants.route)

                                        }
                                    }

                                    override fun onFailure(call: Call<User>, t: Throwable) {
                                        t.message?.let { Log.d("RestaurantList", it) }

                                    }

                                })

                            } else {
                                Toast.makeText(context, "Usuario ya registrado", Toast.LENGTH_SHORT).show()

                            }
                        }
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })




            }) {
            Text(text = "Sign up")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            onClick = {
                navController.navigate(Route.Login.route)

            }) {
            Text(text = "Sign in")
        }
        TextButton(
            onClick = { /*TODO*/ }) {
            Text(text = "Forgot password")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    RestaurantComposeTheme {
        SignUp(navController = rememberNavController())
    }
}
