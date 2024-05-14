package com.example.chatapp.models

data class User(var id: Int = 0,
                var username: String = "",
    var password:String = "",
    var confirmPassword:String  =""
    ) {}