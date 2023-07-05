package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User

class UserAdapter(private var userList : ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    inner class ViewHolder(userView : View) :
        RecyclerView.ViewHolder(userView){

            private lateinit var indexView : TextView
            private lateinit var nameView : TextView
            private lateinit var phoneView : TextView

            init {
                indexView = userView.findViewById(R.id.user_index)
                nameView = userView.findViewById(R.id.user_username)
                phoneView = userView.findViewById(R.id.user_phone)
            }

            fun bind(index : Int, user : User){
                indexView.text = index.toString()
                nameView.text = user.userName
                phoneView.text = user.userPhone
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, userList[position])
    }


}