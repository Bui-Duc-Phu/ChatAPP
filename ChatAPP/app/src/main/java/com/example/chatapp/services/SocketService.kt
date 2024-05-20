package com.example.chatapp.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.chatapp.MyApplication
import com.example.chatapp.R
import com.example.chatapp.Ultils.MySharedPreferences
import com.example.chatapp.Views.Chat
import com.google.gson.JsonParser
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

class SocketService : Service() {

    private lateinit var mSocket: Socket

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(1, createNotification())

        try {
            mSocket = IO.socket("http://192.168.0.102:3001")
            mSocket.emit("joinRoom", (MySharedPreferences.getStringValue(applicationContext,"UID")).toString())
        } catch (e: URISyntaxException) {
            Log.e("SocketService", "URISyntaxException: ${e.message}")
        }

        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on("new_notification", onNewNotification)
        mSocket.connect()
    }

    private val onConnect = Emitter.Listener {
        Log.d("SocketService", "Connected to server")
    }

    private val onNewNotification = Emitter.Listener { args ->
        Log.d("SocketService", "New notification received: ${args[0]}")
        val json = args[1].toString()
        val jsonObject = JsonParser.parseString(json).asJsonObject
        val receiverName = jsonObject.get("receiver_name").asString

        val json2 = args[0].toString()
        val jsonObject2 = JsonParser.parseString(json2).asJsonObject
        val message= jsonObject2.get("message_send").asString


        val json3 = args[2].toString()
        val jsonObject3 = JsonParser.parseString(json3).asJsonObject
        val senderId = jsonObject3.get("senderId").asString



        showNotification(receiverName, message,senderId)
    }

    private fun showNotification(title: String, message: String,senderId:String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        val intent = Intent(this, Chat::class.java)
        intent.putExtra("userId",senderId )
        intent.putExtra("userName", title)
        MySharedPreferences.getStringValue(applicationContext,"UID")
            .also { MyApplication.UID_GLOBAL = it!!.toInt() }
        intent.action = "ACTION_WHEN_NOTIFICATION_CLICKED"
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)



        val notification = NotificationCompat.Builder(this, "your_channel_id")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.baseline_account_circle_24)
            .setContentIntent(pendingIntent) // Đặt PendingIntent vào thông báo
            .setAutoCancel(true) // Tự động huỷ thông báo khi được nhấp
            .build()

        // Hiển thị thông báo
        notificationManager.notify(2, notification)
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket.disconnect()
        mSocket.off(Socket.EVENT_CONNECT, onConnect)
        mSocket.off("new_notification", onNewNotification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "your_channel_id",
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, "your_channel_id")
            .setContentTitle("Socket Service")
            .setContentText("Listening for notifications")
            .setSmallIcon(R.drawable.baseline_energy_savings_leaf_24)
            .build()
    }
}