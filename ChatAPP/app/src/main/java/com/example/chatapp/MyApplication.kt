package com.example.chatapp

import android.app.Application
import android.content.Intent
import android.os.Build
import com.example.chatapp.services.SocketService

class MyApplication : Application() {
    companion object {
        var UID_GLOBAL: Int = 0
    }

    override fun onCreate() {
        super.onCreate()
        notificationService()

    }


    private fun notificationService(){
        val intent = Intent(this, SocketService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}
