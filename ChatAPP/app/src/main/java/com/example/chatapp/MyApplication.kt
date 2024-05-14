package com.example.chatapp

import android.app.Application

class MyApplication : Application() {
    companion object {
        var UID_GLOBAL: Int = 0
    }

    override fun onCreate() {
        super.onCreate()
        // Khởi tạo giá trị ban đầu của biến toàn cục ở đây nếu cần
    }
}
