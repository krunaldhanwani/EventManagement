package com.example.eventapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.EventDetailActivity
import com.example.eventapp.R
import com.example.eventapp.model.Event

class EventListAdapter(private var eventList: ArrayList<Event>) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.raw_event_list, parent, false)
        )
    }

    override fun getItemCount() = eventList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvEventName.text = eventList[position].eventName
        holder.tvDate.text = eventList[position].eventDate

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context,EventDetailActivity::class.java)
                .putExtra("eventName",eventList[position].eventName)
                .putExtra("organizersMobileNumber",eventList[position].organizersMobileNumber)
                .putExtra("organizersName",eventList[position].organizersName)
                .putExtra("participatedGender",eventList[position].participatedGender)
                .putExtra("eventField",eventList[position].eventField)
                .putExtra("eventDate",eventList[position].eventDate))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvEventName: TextView = itemView.findViewById(R.id.tvEventName)
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }
}