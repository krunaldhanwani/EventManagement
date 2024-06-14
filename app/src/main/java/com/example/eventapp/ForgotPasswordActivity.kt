package com.example.eventapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventapp.R
import kotlinx.android.synthetic.main.raw_header.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        inIt()
    }

    private fun inIt() {
        tvHeader.text = "Forget Password"
        ivBackButton.setOnClickListener {
            finish()
        }
    }
}