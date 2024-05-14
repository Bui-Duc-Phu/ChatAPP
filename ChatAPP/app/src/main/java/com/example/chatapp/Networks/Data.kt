package com.example.chatapp.Networks

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.chatapp.MyApplication
import com.example.chatapp.Views.UserLists
import com.example.chatapp.Views.User_res
import com.example.chatapp.models.User
import com.example.messengerapp.model.Chat
import com.example.restfull_api_1.Networks.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Data {

    fun postMessageMethod(context: Context,mess: Chat,callback: (Boolean) -> Unit){
        val call = RetrofitClient.instance.postMessage(mess)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                 //   Toast.makeText(context,"post mess thanh cong", Toast.LENGTH_SHORT).show()
                    println("thanh cong")
                    callback(true)

                } else {
                    println("send mes that bại")
                    val errorMessage = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorMessage)
                    val message = jsonObject.getString("error")
                    Toast.makeText(context, "$message", Toast.LENGTH_SHORT).show()
                    callback(false)
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                println(t.message)
            }
        })
    }

     fun getMessage(context: Context,receiver_id:Int,sender_id:Int,callback: (List<Chat>) -> Unit) {
        val call = RetrofitClient.instance.getMessage(receiver_id.toString(),sender_id.toString())
        call.enqueue(object : Callback<List<Chat>> {
            override fun onResponse(call: Call<List<Chat>>, response: Response<List<Chat>>) {
                if (response.isSuccessful) {
                    response.body().also { callback(it!!) }
                }else{
                    Toast.makeText(context, "get data false 2", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Chat>>, t: Throwable) {
                Toast.makeText(context, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                println("${t.message}")
            }
        })
    }








}