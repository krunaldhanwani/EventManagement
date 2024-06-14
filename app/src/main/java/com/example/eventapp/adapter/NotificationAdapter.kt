package com.example.eventapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.R

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.raw_notification,parent,false))
    }

    override fun getItemCount() = 20


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.tvNotification.text = notificationList[position].notification
//
//        holder.tvNotificationTime.text = TimeAgo.
//
//        getTimeAgo(notificationList[position].created_at)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
          var tvNotification : TextView = itemView.findViewById(R.id.tvNotification)
          var tvNotificationTime : TextView = itemView.findViewById(R.id.tvNotificationTime)
    }
}