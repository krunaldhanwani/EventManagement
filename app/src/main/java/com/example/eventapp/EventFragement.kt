package com.example.eventapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventapp.adapter.EventListAdapter
import com.example.eventapp.model.Event
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_event_fragement.*

class EventFragement : Fragment() {
    private lateinit var eventListAdapter: EventListAdapter
    var eventList = ArrayList<Event>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_fragement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inIt()
    }

    private fun inIt() {
        readEvent()
//        eventListAdapter = EventListAdapter(eventList)
//        rvMyAddress.layoutManager = LinearLayoutManager(requireContext())
//        rvMyAddress.adapter = eventListAdapter
    }

    private fun readEvent() {
        var databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Events")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val event = dataSnapShot.getValue(Event::class.java)
                    eventList.add(event!!)
                }
            eventListAdapter = EventListAdapter(eventList)
            rvMyAddress.layoutManager = LinearLayoutManager(requireContext())
            rvMyAddress.adapter = eventListAdapter
        }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
            }


        })
    }
}