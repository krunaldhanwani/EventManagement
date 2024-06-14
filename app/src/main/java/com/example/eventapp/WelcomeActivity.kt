package com.example.eventapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.activity_welcome.btnSignUp
import kotlinx.android.synthetic.main.raw_header.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        inIt()
    }

    private fun inIt() {
        ivBackButton.visibility = View.GONE

        tvHeader.text = "welcome"

        llCustomer.setOnClickListener {
            rbServiceProvider.isChecked = false
            rbCustomer.isChecked = true
            rbAffiliate.isChecked = false
        }

        llServiceProvider.setOnClickListener {
            rbCustomer.isChecked = false
            rbServiceProvider.isChecked = true
            rbAffiliate.isChecked = false
        }

        llAffiliate.setOnClickListener {
            rbCustomer.isChecked = false
            rbServiceProvider.isChecked = false
            rbAffiliate.isChecked = true
        }
        btnSignUp.setOnClickListener {
            if (rbCustomer.isChecked)  {
                startActivity(Intent(this, LoginActivity::class.java)
                    .putExtra("type" , "student"))
                finish()
            } else if (rbAffiliate.isChecked) {
                startActivity(Intent(this, LoginActivity::class.java)
                    .putExtra("type" , "admin"))
                finish()

            } else {
                startActivity(Intent(this, LoginActivity::class.java)
                    .putExtra("type" , "organizer"))
                finish()

            }
        }
    }

}