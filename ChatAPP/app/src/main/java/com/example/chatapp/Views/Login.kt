package com.example.chatapp.Views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapp.MyApplication
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.models.User
import com.example.restfull_api_1.Networks.RetrofitClient
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody


class Login : AppCompatActivity() {

    val socket: Socket = IO.socket("http://192.168.0.102")

    private  val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init_()
    }

    private fun init_() {

        binding.loginButton.setOnClickListener {
            checkedLogin()
        }

        binding.signupText.setOnClickListener {
            startActivity(Intent(this@Login,SignUp::class.java))
        }

    }

    private fun checkedLogin() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()
        login(User(0,username,password,"")){
            getUser(username){ MyApplication.UID_GLOBAL  = it}
        }




    }



    private fun login(user: User,callback: (Boolean) -> Unit){
        val call = RetrofitClient.instance.login(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@Login,"login thành công", Toast.LENGTH_SHORT).show()
                    callback(true)
                } else {
                    val errorMessage = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorMessage)
                    val message = jsonObject.getString("message")
                    Toast.makeText(this@Login, "$message", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@Login, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                println(t.message)
            }
        })
    }



    private fun getUser(username:String,callback: (Int) -> Unit) {
        val call = RetrofitClient.instance.getUser(username)
        call.enqueue(object : Callback<User_res> {
            override fun onResponse(call: Call<User_res>, response: Response<User_res>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    callback(user!!.uid)
                    startActivity(Intent(this@Login,UserLists::class.java))
                } else {
                    val errorMessage = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorMessage)
                    val message = jsonObject.getString("message")
                    Toast.makeText(this@Login, "$message", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User_res>, t: Throwable) {
                Toast.makeText(this@Login, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                println("${t.message}")
            }
        })
    }





}



