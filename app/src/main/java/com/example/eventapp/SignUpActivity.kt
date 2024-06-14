package com.example.eventapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.eventapp.model.user
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.raw_header.*
import java.lang.Double
import java.text.DecimalFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var references: DatabaseReference? = null

    private var gender = ""
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
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
        dpd.datePicker.maxDate = System.currentTimeMillis()

        dpd.show()
    }

    private fun inIt() {
        type = intent.getStringExtra("type")!!

        var account = getString(R.string.Already_have_an_account)
        account = account.replace("Login", "<font color='#01438C'>Login</font>")
        tvLogin.text = Html.fromHtml(account)

        tvHeader.text = "Sign Up"
        ivBackButton.setOnClickListener {
            finish()
        }


        ivPasswordShowHideButton.setOnClickListener{ ShowHidePass(etPassword , ivPasswordShowHideButton ) }

        ivPasswordconfirmShowHideButton.setOnClickListener{ ShowHidePass(etPasswordConfirm , ivPasswordconfirmShowHideButton ) }

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
            gender = "other"
        }


        btnSignUp.setOnClickListener {
            tvLogin.performClick()
        }

        tvBirthDate.setOnClickListener { birthDatePicker() }

//        tvField.setOnClickListener {
            if (tvField != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, resources.getStringArray(R.array.general1)

                )
                tvField.adapter = adapter
            }
//        }

        tvLogin.setOnClickListener {
            registerUser(etName.text.toString(),etEmail.text.toString(),etPassword.text.toString(),etMobileNumber.text.toString(),tvField.toString(),gender,tvBirthDate.text.toString())
//            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    fun ShowHidePass(editText: EditText, imageView: ImageView) {

        /*  imageView.setOnClickListener {*/
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
        /*}*/
    }

    private fun registerUser(userName: String, email: String, password: String, mobileNumber:String,field:String,gender:String,dob:String) {
        auth = FirebaseAuth.getInstance()
//        Toast.makeText(this,"dd",Toast.LENGTH_LONG).show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
//                    Toast.makeText(this,"dones",Toast.LENGTH_LONG).show()
                    var useras: FirebaseUser? = auth.currentUser
                    var userId: String = useras!!.uid

                    var user = user(userId,userName,email,type,mobileNumber,field,gender,dob)

                    references = FirebaseDatabase.getInstance().reference

                references!!.child("user").push().setValue(user).addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            startActivity(Intent( this, LoginActivity::class.java))
                            finish()
                            Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
    }
}