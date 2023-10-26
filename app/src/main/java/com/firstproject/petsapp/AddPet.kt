package com.firstproject.petsapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class AddPet : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_pet)
        
        val galleryBtn = findViewById<Button>(R.id.galleryBtn)
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setBackgroundColor(Color.parseColor("#FF00A5FF"))
        
        
        galleryBtn.setOnClickListener {
            
            
            /* val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) */
            try {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                
                
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show()
            }
            
        }
        val date = findViewById<DatePicker>(R.id.date)
        val dcl: DatePicker.OnDateChangedListener =
            DatePicker.OnDateChangedListener { DatePicker, year, monthOfYear, dayOfMonth ->
                if (monthOfYear + 1 != 10) {
                    val k = "date: " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    Toast.makeText(this, k, Toast.LENGTH_SHORT).show()
                }
            }
        
        
        saveBtn.setOnClickListener {
            
            val petName = findViewById<EditText>(R.id.petName).text.toString()
            val species = findViewById<EditText>(R.id.species).text.toString()
            val breed = findViewById<EditText>(R.id.breed).text.toString()
            val petType = findViewById<RadioGroup>(R.id.type)
            val cat = findViewById<RadioButton>(R.id.cat)
            val dog = findViewById<RadioButton>(R.id.dog)
            val otherPet = findViewById<RadioButton>(R.id.other)
            val date = findViewById<DatePicker>(R.id.date)
            var s: String = ""
            
            if (cat.isChecked) {
                s = s + cat.text
            } else if (dog.isChecked) {
                s = s + dog.text
            } else if (otherPet.isChecked) {
                s = s + otherPet.text
            }
            
            Toast.makeText(
                this,
                "Pet Name: " + petName + "\nSpecies: " + species + "\nBreed: " + breed + "\nPet " +
                        "Type: " + s,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        val image = findViewById<ImageView>(R.id.image)
        
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            
            val imageUri = data?.data
            image.setImageURI(Uri.parse(imageUri.toString()));
            
        }
    }
}