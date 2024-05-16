package com.example.chatapp.Views

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.MyApplication
import com.example.chatapp.Networks.Data
import com.example.chatapp.Ultils.MySharedPreferences
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.models.TypeMessage
import com.example.messengerapp.adapter.ChatAdapter
import com.example.messengerapp.model.Chat
import io.socket.client.IO
import org.json.JSONObject

import io.socket.client.Socket
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONException



class Chat : AppCompatActivity() {
    private val binding:ActivityChatBinding  by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }
    private var receiverId: Int? = null
    private var userName: String? = null

    private val socket: Socket by lazy {
        IO.socket("http://192.168.0.102:3001")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        receiverId = intent.getStringExtra("userId")?.toIntOrNull()
        userName = intent.getStringExtra("userName").toString()

        binding.recylerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        connectToServer()

        init_()

        socket.emit("joinRoom", (receiverId?.plus(MyApplication.UID_GLOBAL)).toString())






    }

    private fun init_() {


        binding.messageEdt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Xử lý sự kiện ở đây
                val message = binding.messageEdt.text.toString()
                if(message.isEmpty()){
                    Toast.makeText(applicationContext, "Text is empty", Toast.LENGTH_SHORT).show()
                }else {
                    checked()
                }
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
                true // Trả về true để xác nhận rằng sự kiện đã được xử lý
            } else {
                false
            }
        }

        receiverId?.let {
            Data.getMessage(this, it,MyApplication.UID_GLOBAL){
                println("Message List  : " + it)
                val adapter = ChatAdapter(this,it)
                binding.recylerview.adapter = adapter
                val lastItemPosition = adapter.itemCount - 1
                if (lastItemPosition >= 0) {
                    binding.recylerview.scrollToPosition(lastItemPosition)
                }
            }
        }
    }

    private fun connectToServer() {
        // Kết nối tới máy chủ
        socket.on(Socket.EVENT_CONNECT) {
            println("Connected to server")
        }
        socket.on("123") { data ->
            data.forEachIndexed { index, item ->
                try {
                    val jsonObject = JSONObject(item.toString())
                    val message = jsonObject.getString("message").toString()
                    println("Received message: $message")
                    if(message.equals("loading server")){
                        receiverId?.let {
                            Data.getMessage(this, it,MyApplication.UID_GLOBAL){
                                println("Message List  : " + it)
                                val adapter = ChatAdapter(this,it)
                                binding.recylerview.adapter = adapter
                                val lastItemPosition = adapter.itemCount - 1
                                if (lastItemPosition >= 0) {
                                    binding.recylerview.scrollToPosition(lastItemPosition)
                                }
                            }
                        }
                    }
                } catch (e: JSONException) {
                    println("Error parsing JSON: ${e.message}")
                }
            }
        }

        socket.on(Socket.EVENT_DISCONNECT) {
            println("Disconnected from server")
        }

        socket.connect()
    }



    private fun checked() {
        val message = binding.messageEdt.text.toString().trim()
        val senderId = MyApplication.UID_GLOBAL
        val typeMessage = TypeMessage.text.toString()
        receiverId?.let { sendMessage(senderId, it,typeMessage,message) }

    }




    private fun sendMessage(senderId:Int,receiverId:Int,typeMessage :String,message:String) {
        val mess = Chat(senderId,receiverId,message,typeMessage)
        Data.postMessageMethod(this,mess){
            if(it) binding.messageEdt.setText("")
            reconnectToServer((receiverId + MyApplication.UID_GLOBAL).toString(),message)
        }
    }

    private fun reconnectToServer(room:String,message:String) {

        val data = mapOf(
            "room" to room,
            "receiver" to receiverId.toString(),
            "message_send" to message,
            "receiver_name" to userName,
            "senderId"  to MyApplication.UID_GLOBAL.toString(),

        )
        val jsonString = Json.encodeToString(data)
        socket.emit("key", jsonString)
    }





}