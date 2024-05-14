package com.example.chatapp.Views

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivitySignUpBinding
import com.example.chatapp.models.User
import com.example.restfull_api_1.Networks.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class SignUp : AppCompatActivity() {
    private val binding : ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.usernameEdt.setText("")
//        binding.passwordEdt.setText("")
//        binding.confirmPasswrodEdt.setText("")



        init_()




    }

    private fun init_() {
        binding.loginButton.setOnClickListener {
            checkedSignUp()
        }

        binding.signinText.setOnClickListener {
            startActivity(Intent(this@SignUp,Login::class.java))
        }
    }

    private fun checkedSignUp() {
        val username  = binding.usernameEdt.text.toString().trim()
        val password = binding.passwordEdt.text.toString().trim()
        val confirmPassword = binding.confirmPasswrodEdt.text.toString().trim()
        val randomNumber = Random.nextInt(100000, 999999)
        signUp(User(randomNumber,username,password,confirmPassword))

    }



    private  fun signUp(user:User){
        val call = RetrofitClient.instance.SignUp(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SignUp,"signup thành công", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUp,UserLists::class.java))
                } else {
                    val errorMessage = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorMessage)
                    val message = jsonObject.getString("message")
                    Toast.makeText(this@SignUp, "$message", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@SignUp, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                println(t.message)
            }
        })
    }
}