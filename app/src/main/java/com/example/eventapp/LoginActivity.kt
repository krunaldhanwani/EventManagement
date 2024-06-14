package com.example.eventapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.eventapp.model.user
import com.example.eventapp.utils.Constant
import com.example.eventapp.utils.MyPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.raw_header.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private var type = ""
    private var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inIt()
    }

    private fun inIt() {
        type = intent.getStringExtra("type").toString()
        auth = FirebaseAuth.getInstance()
        var account = getString(R.string.Already_have_an_account_sign_up)
        account = account.replace("Signup", "<font color='#01438C'>Signup</font>")
        tvLogin.text = Html.fromHtml(account)

        ivPasswordShowHideButton.setOnClickListener { ShowHidePass(etPassword , ivPasswordShowHideButton) }

        tvHeader.text = "Log In"

        tvForgotPassword.setOnClickListener { startActivity(Intent(this,ForgotPasswordActivity::class.java)) }

        ivBackButton.setOnClickListener {
            finish()
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java)
                .putExtra("type",type))
        }

        btnSignUp.setOnClickListener {
                if (TextUtils.isEmpty(etName.text.toString()) && TextUtils.isEmpty(etPassword.text.toString())) {
                    Toast.makeText(this,"email and password required", Toast.LENGTH_SHORT).show()
                } else {
                    auth.signInWithEmailAndPassword(etName.text.toString() , etPassword.text.toString())
                        .addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                userList()
                                etName.setText("")
                                etPassword.setText("")
                                Log.e("qqqqq",flag.toString())
//                                if (flag == true) {
//                                    if (type == "student") {
//                                        startActivity(Intent(this,StudentDashboard::class.java))
//                                    } else if (type == "organizer") {
//                                        startActivity(Intent(this,OrganizersMainActivity::class.java))
//                                    } else {
//                                        startActivity(Intent(this,MainActivity::class.java))
//                                    }
//                                } else{
//                                    Toast.makeText(this,"user does't exits",Toast.LENGTH_SHORT).show()
//                                }
                            } else {
                                Toast.makeText(this,"email and password is wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
        }
    }

    private fun ShowHidePass(editText: EditText, imageView: ImageView) {
        if (editText.transformationMethod == PasswordTransformationMethod.getInstance()) {
            imageView.setImageResource(R.drawable.ic_show_pass)
            //Show Password
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            imageView.setImageResource(R.drawable.ic_hide_pass)
            //Hide Password
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        editText.setSelection( editText.text.toString().length )
    }

    private fun userList() {
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("user")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (dataSnapShot: DataSnapshot in snapshot.children) {

                    val user = dataSnapShot.getValue(user::class.java)
                    if (user!!.userId == firebase.uid) {

                        if (user.type == type) {
                           /* Constant.ISLOGIN2 = "True"
                            Constant.ISLOGIN = "True"*/
                            MyPreferences.saveStringInPreference(this@LoginActivity,Constant.isLogin,"true")
                            Log.e("11111111",""+type)
                            MyPreferences.saveStringInPreference(this@LoginActivity,Constant.Type,type)
                            Log.e("ISLOGIN","---->"+MyPreferences.getFromPreferences(this@LoginActivity, Constant.Type))
                            when (type) {
                                "student" -> {
//                                    MyPreferences.saveStringInPreference(this@LoginActivity,Constant.Type,type)
                                    startActivity(Intent(this@LoginActivity,StudentDashboard::class.java))
                                    finish()
                                }
                                "organizer" -> {
//                                    MyPreferences.saveStringInPreference(this@LoginActivity,Constant.Type,type)
                                    startActivity(Intent(this@LoginActivity,OrganizersMainActivity::class.java))
                                    finish()
                                }
                                else -> {
//                                    MyPreferences.saveStringInPreference(this@LoginActivity,Constant.Type,type)
                                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                                    finish()
                                }
                            }
                        } else{
                            Toast.makeText(this@LoginActivity,"user does't exits",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"hello",Toast.LENGTH_SHORT).show()
                MyPreferences.saveStringInPreference(this@LoginActivity,Constant.isLogin,"false")
            }

        })
    }



}