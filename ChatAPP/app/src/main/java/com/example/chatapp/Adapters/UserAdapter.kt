package com.example.messengerapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.Views.Chat
import com.example.chatapp.Views.User_res

import com.example.chatapp.databinding.UserAdapterBinding
import com.example.chatapp.models.User


class UserAdapter : RecyclerView.Adapter<UserAdapter.viewholer>{
    lateinit var binding : UserAdapterBinding
    val content:Context
    val list : List<User_res>
    constructor(content: Context, list: List<User_res>) {
        this.content = content
        this.list = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholer {
       binding = UserAdapterBinding.inflate(LayoutInflater.from(content),parent,false)
        return viewholer(binding.root)
    }
    override fun onBindViewHolder(holder: viewholer, position: Int) {
        holder.apply {
            val model = list[position]
            userName.text = model.username

            itemView.setOnClickListener {
                val intent = Intent(content,Chat::class.java)
                intent.putExtra("userId",model.uid.toString() )
                intent.putExtra("userName", model.username)
                content.startActivity(intent)
            }




        }
    }
    override fun getItemCount(): Int {
       return  list.size
    }
    inner class viewholer(view:View):RecyclerView.ViewHolder(view){
        val userName = binding.nameUser

    }


}
