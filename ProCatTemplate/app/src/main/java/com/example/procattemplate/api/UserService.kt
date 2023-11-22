package com.example.procattemplate.api

import retrofit2.Call
import retrofit2.http.GET

/* Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime */

interface UserService {
    @GET("/api")
    fun getUsers(): Call<UserResponse>
}