package com.example.restfull_api_1.Networks


import com.example.chatapp.Views.User_res
import com.example.chatapp.models.User
import com.example.messengerapp.model.Chat
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/get-user/{name}")
    fun getUser(@Path("name") name: String): Call<User_res>


    @GET("/get-receiver/{id}")
    fun getAllReceiver(@Path("id") id: String): Call<List<User_res>>

    @GET("/get-message/{receiver_id}/{sender_id}")
    fun getMessage(@Path("receiver_id") receiver_id: String, @Path("sender_id") sender_id: String): Call<List<Chat>>





    @POST("/sign-up")
    fun SignUp(@Body user: User): Call<Void>

    @POST("/login")
    fun login(@Body user: User): Call<Void>

    @POST("/post-message")
    fun postMessage(@Body mess: Chat): Call<Void>





    @DELETE("/v1/deletestudent/{id}")
    fun deleteStudent(@Path("id") id: Int): Call<Void>

    @PUT("/v1/putname")
    fun putStudent(@Body student: Chat): Call<Void>

    @PATCH("/v1/updateAge")
    fun patchStudent(@Body student: Chat): Call<Void>



}
