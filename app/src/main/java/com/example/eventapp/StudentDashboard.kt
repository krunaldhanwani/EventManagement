package com.example.eventapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_student_dashboard.*

class StudentDashboard : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var eventFragement: EventFragement
    private lateinit var searchFragment: SearchFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)
        inIt()
    }

        private fun inIt() {
        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        eventFragement = EventFragement()
        changeFragment(0)
        llHome.setOnClickListener { changeFragment(0) }
            llSearch.setOnClickListener { changeFragment(1) }
            llBooking.setOnClickListener { changeFragment(2) }

        customIconView.setOnClickListener {  startActivity(Intent(this,StudentNotificationActivity::class.java)) }
        }


    private fun changeFragment(arg : Int) {
        ivHome.setImageResource(R.drawable.ic_home_unselected_icon)
        ivSearch.setImageResource(R.drawable.ic_search_fragment)
        ivBooking.setImageResource(R.drawable.ic_booking)
        ivHomeSelected.visibility = View.GONE
        ivSearchSelected.visibility = View.GONE
        ivBookingSelected.visibility = View.GONE
        when (arg) {
            0 -> {
                ivHomeSelected.visibility = View.VISIBLE
                ivHome.setImageResource(R.drawable.ic_home_selected_icon)
                transactFragment(homeFragment)
            }1 -> {
            ivSearchSelected.visibility = View.VISIBLE
            ivSearch.setImageResource(R.drawable.ic_search_selected_icon)
                transactFragment(searchFragment)
            } 2 -> {
            ivBookingSelected.visibility = View.VISIBLE
            ivBooking.setImageResource(R.drawable.ic_booking_selected_icon)
                transactFragment(eventFragement)
            }
        }
    }

private fun transactFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.llMain, fragment).commit()
}
}
