package pe.edu.cibertec.restaurantcompose.data.repository

import pe.edu.cibertec.restaurantcompose.data.model.User
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import pe.edu.cibertec.restaurantcompose.data.remote.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val userService: UserService = ApiClient.getUserInterface()
) {

    fun login(username: String, password: String, callback: (Boolean) -> Unit) {
        userService.login(username, password).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.isNotEmpty()) {
                        callback(true)
                    } else {
                        callback(false)
                    }
                } else
                    callback(false)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(false)
            }

        })
    }
}