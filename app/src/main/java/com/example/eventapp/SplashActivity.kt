package com.example.eventapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.eventapp.utils.Constant
import com.example.eventapp.utils.MyPreferences
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        fetchAndSaveFCM()
        Handler(
            Looper.getMainLooper()).postDelayed({
            if (MyPreferences.getFromPreferences(this, Constant.isLogin) == "true") {
                if (MyPreferences.getFromPreferences(this, Constant.Type) == "student"){
                    startActivity(Intent(this@SplashActivity,StudentDashboard::class.java))
                    finish()
                } else  if (MyPreferences.getFromPreferences(this, Constant.Type) == "organizer"){
                    startActivity(Intent(this@SplashActivity,OrganizersMainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    finish()
                }
            } else {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        },3000)
    }


//    /** this function used for check and fetch firebase token **/
//    private fun fetchAndSaveFCM() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) { return@OnCompleteListener }
//            if (task.result != MyPreferences.getFromPreferences(this,Constant.FCM_TOKEN)) {
//                Log.e("TOKENOFFB","-->"+task.result)
//                MyPreferences.saveStringInPreference(this,Constant.FCM_TOKEN , task.result)
//            }
//        })
//    }
}