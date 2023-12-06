package com.example.procattemplate.api

import com.example.procattemplate.data_storage.DataCoordinator
import com.example.procattemplate.data_storage.updateUserEmail
import okhttp3.FormBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiCalls {
    companion object {
        val shared = ApiCalls()
        const val identifier = "[ApiCalls]"
    }

    public fun runApi(url: String)  {

        val service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        /* Calls the endpoint set on getUsers (/api) from UserService using enqueue method
         * that creates a new worker thread to make the HTTP call */
        service.getUsers().enqueue(object : Callback<UserResponse> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                DataCoordinator.shared.updateUserEmail("ERROR 404")
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                /* This will print the response of the network call to the Logcat */

                DataCoordinator.shared.updateUserEmail(response.body().toString().substring(27, 55))

            }

        })

    }

    public fun postApi(url: String)  {

        val service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        /* Calls the endpoint set on getUsers (/api) from UserService using enqueue method
         * that creates a new worker thread to make the HTTP call */

        val requestBody: RequestBody = FormBody.Builder()
            .add("username", "test1")
            .add("password", "test2")
            .build()

        service.setUsers(requestBody).enqueue(object : Callback<ResponseBody> { //ResponseBody

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                DataCoordinator.shared.updateUserEmail("ERROR 404")
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                /* This will print the response of the network call to the Logcat */

                DataCoordinator.shared.updateUserEmail(response.body().toString().substring(27, 55))

            }

        })

    }

}








