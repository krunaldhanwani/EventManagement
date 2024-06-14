package com.example.eventapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        inIt()
    }
    private fun inIt() {
        tvEventName.text = intent.getStringExtra("eventName")
        tvEventOrganizationName.text = intent.getStringExtra("organizersName")
        tvEventDate.text = intent.getStringExtra("eventDate")
        tvEventParticipate.text = intent.getStringExtra("participatedGender")
        tvEventField.text = intent.getStringExtra("eventField")
        tvEventContact.text = intent.getStringExtra("organizersMobileNumber")
    }
}