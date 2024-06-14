package com.example.eventapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eventapp.model.Event
import com.example.eventapp.notification.FcmNotificationsSender
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_organizers_main.*
import kotlinx.android.synthetic.main.raw_header.*
import java.lang.Double
import java.text.DecimalFormat
import java.util.*
import kotlin.String

class OrganizersMainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var references: DatabaseReference? = null
    private var gender = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizers_main)
        inIt()
    }

    private fun birthDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val mFormat = DecimalFormat("00")

        val dpd = DatePickerDialog(this,R.style.MyDatePickerDialogTheme,{ view, year, monthOfYear, dayOfMonth ->
            val dat  =  mFormat.format(Double.valueOf(year.toDouble())).toString() + "-" + mFormat.format(
                Double.valueOf((monthOfYear + 1).toDouble())) + "-" + mFormat.format(Double.valueOf(dayOfMonth.toDouble()))
            tvBirthDate.text = dat
        }, year, month, day)
        dpd.datePicker.minDate = System.currentTimeMillis()

        dpd.show()
    }

    private fun inIt() {
        tvHeader.text = "Events Management"
        ivBackButton.setOnClickListener {
            finish()
        }
        llMale.setOnClickListener {
            rbMale.isChecked = true
            rbFemale.isChecked = false
            rbOther.isChecked = false
            gender = "Male"
        }

        llFemale.setOnClickListener {
            rbFemale.isChecked = true
            rbMale.isChecked = false
            rbOther.isChecked = false
            gender = "Female"
        }

        llOther.setOnClickListener {
            rbMale.isChecked = false
            rbFemale.isChecked = false
            rbOther.isChecked = true
            gender = "all"
        }

        btnSignUp.setOnClickListener {
            notification()
            registerEvent(etName.text.toString(),etEmail.text.toString(),etMobileNumber.text.toString(),gender,tvBirthDate.text.toString(),tvField.toString())
//            startActivity(Intent(this,DoneActivity::class.java))
        }

        tvBirthDate.setOnClickListener { birthDatePicker() }

        if (tvField != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.general1))
            tvField.adapter = adapter
        }
    }


    private fun registerEvent(eventName: String, organizersName: String, organizersMobileNumber: String,participatedGender:String,eventDate:String, eventField:String) {
        auth = FirebaseAuth.getInstance()

        var event = Event(eventName,organizersName,organizersMobileNumber,participatedGender,eventDate,eventField)

        references = FirebaseDatabase.getInstance().reference

        references!!.child("Events").push().setValue(event).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                startActivity(Intent(this,DoneActivity::class.java))
                Toast.makeText(this,"success", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun notification() {

// See documentation on defining a message payload.

//// See documentation on defining a message payload.
//        val message: Message = Message.builder()
//            .putData("score", "850")
//            .putData("time", "2:45")
//            .setTopic(topic)
//            .build()
//
//// Send a message to the devices subscribed to the provided topic.
//
//// Send a message to the devices subscribed to the provided topic.
//        val response: String = FirebaseMessaging.getInstance().send(message)
//// Response is a message ID string.
//// Response is a message ID string.
//        println("Successfully sent message: $response")
//        /*FirebaseMessaging.getInstance().subscribeToTopic("all")
//            .addOnCompleteListener { task ->
//                var msg = "Subscribed"
//                if (!task.isSuccessful) {
//                    msg = "Subscribe failed"
//                }
//                Log.d("111112222", msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//            }*/

2
FirebaseMessaging.getInstance().subscribeToTopic("all")

        if (etName.text.toString().isNotEmpty()  && etEmail.text.toString().isNotEmpty() ) {
//            FcmNotificationsSender(MyPreferences.getFromPreferences(this, Constant.FCM_TOKEN),etName.text.toString(),etEmail.text.toString(),applicationContext,this).SendNotifications()
            FcmNotificationsSender("/topics/all",etName.text.toString(),etEmail.text.toString(),applicationContext,this).SendNotifications()
        }
    }
}