package com.example.eventapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventapp.adapter.NotificationAdapter
import kotlinx.android.synthetic.main.activity_student_notification.*

class StudentNotificationActivity : AppCompatActivity() {

    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_notification)
        initData()
    }

    fun initData()  {
        val layoutManager = LinearLayoutManager(this)
        notificationAdapter = NotificationAdapter()
        rvNotification.layoutManager = layoutManager
        rvNotification.adapter = notificationAdapter
    }
}