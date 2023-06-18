package pe.edu.cibertec.restaurantcompose.data.repository

import pe.edu.cibertec.restaurantcompose.data.model.User
import pe.edu.cibertec.restaurantcompose.data.remote.ApiClient
import pe.edu.cibertec.restaurantcompose.data.remote.service.UserService
import pe.edu.cibertec.restaurantcompose.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val userService: UserService = ApiClient.getUserInterface()
) {

    fun login(username: String, password: String, callback: (Result<Boolean>) -> Unit) {
        userService.login(username, password).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.isNotEmpty()) {
                        callback(Result.Success(true))
                    } else {
                        callback(Result.Error("Login incorrect"))
                    }
                } else
                    callback(Result.Error("Data not found"))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(Result.Error("Login not available"))
            }

        })
    }

    private fun validateUser(username: String, callback: (Result<Boolean>) -> Unit){

        userService.validateUser(username).enqueue(object: Callback<List<User>>{

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.isEmpty()) {
                        callback(Result.Success(true))
                    } else {
                        callback(Result.Error("Username already registered", false))
                    }
                } else
                    callback(Result.Error("Data not found"))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(Result.Error("Validate not available"))
            }
        })
    }

    fun createUser(username: String, password: String, callback: (Result<Boolean>) -> Unit) {
        validateUser(username){result ->
            if (result is Result.Success) {
                userService.createUser(User(username, password)).enqueue(object: Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful && response.body() != null){
                            callback(Result.Success(true))
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        callback(Result.Error("Create user not available"))
                    }
                })
            } else {
                callback(Result.Error(result.message.toString()))
            }
        }
    }


}