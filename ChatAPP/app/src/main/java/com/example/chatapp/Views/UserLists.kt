package com.example.chatapp.Views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.MyApplication
import com.example.chatapp.databinding.ActivityUserListsBinding
import com.example.messengerapp.adapter.UserAdapter
import com.example.restfull_api_1.Networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLists : AppCompatActivity() {

    private val binding : ActivityUserListsBinding  by lazy {
        ActivityUserListsBinding.inflate(layoutInflater)
    }
    val uid : Int = MyApplication.UID_GLOBAL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        init_()
    }

    private fun init_() {

      recylerview()





    }

    private fun recylerview() {
        getAllReceiver{
            val adapter = UserAdapter(this,it)
            binding.recylerview.adapter = adapter
        }
    }

    private fun getAllReceiver(callback: (List<User_res>) -> Unit) {
        val call = RetrofitClient.instance.getAllReceiver(MyApplication.UID_GLOBAL.toString())
        call.enqueue(object : Callback<List<User_res>> {
            override fun onResponse(call: Call<List<User_res>>, response: Response<List<User_res>>) {
                if (response.isSuccessful) {
                    response.body().also { callback(it!!) }
                }else{
                    Toast.makeText(this@UserLists, "get data false 1", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<User_res>>, t: Throwable) {
                Toast.makeText(this@UserLists, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                println("${t.message}")
            }
        })
    }
















}