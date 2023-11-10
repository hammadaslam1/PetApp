package com.firstproject.petsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class All_Pets : AppCompatActivity() {
    
    private lateinit var dbref: DatabaseReference
    private lateinit var petRecyclerview: RecyclerView
    private lateinit var petArrayList: ArrayList<Pet>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_pets)
        
        val addbtn = findViewById<FloatingActionButton>(R.id.addnewbtn)
        val google = findViewById<Button>(R.id.googlemap)
        
        google.setOnClickListener {
            val i = Intent(applicationContext, MapsActivity::class.java)
            startActivity(i)
        }
        
        addbtn.setOnClickListener {
            val i = Intent(this, AddPet::class.java)
            startActivity(i)
        }
        
        petRecyclerview = findViewById<RecyclerView>(R.id.pet_list)
        petRecyclerview.layoutManager = LinearLayoutManager(this)
        petRecyclerview.setHasFixedSize(true)
        
        petArrayList = arrayListOf()
        getUserData()
    }
    
    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("pet")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (petSnap in snapshot.children) {
                        val pet = petSnap.getValue(Pet::class.java)
                        
                        petArrayList.add(pet!!)
                    }
                    petRecyclerview.adapter = petsAdapter(petArrayList)
                    /* Toast.makeText(
                        applicationContext, "data exists: $petArrayList", Toast
                            .LENGTH_LONG
                    ).show() */
                }
            }
            
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "faliure", Toast.LENGTH_SHORT).show()
            }
        })
    }
    
}