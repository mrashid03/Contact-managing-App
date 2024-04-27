package com.example.loginandregister

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog_box)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box))

        val buttonAdd = findViewById<Button>(R.id.btnAdd)
        val btnName = findViewById<EditText>(R.id.Name)
        val btnMail = findViewById<EditText>(R.id.Email)
        val btnNumber= findViewById<EditText>(R.id.Number)
        val buttonOk = dialog.findViewById<Button>(R.id.btnOk)


        buttonAdd.setOnClickListener {
            val name = btnName.text.toString()
            val mail = btnMail.text.toString()
            val phoneNumber = btnNumber.text.toString()


            val contact = Contacts(name, mail, phoneNumber)
            database = FirebaseDatabase.getInstance().getReference("contacts")
            database.child(phoneNumber).setValue(contact).addOnSuccessListener {

                btnName.text?.clear()
                btnMail.text?.clear()
                btnNumber.text?.clear()

                Toast.makeText(this, "User Done", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }

        buttonOk.setOnClickListener {
            dialog.dismiss()
        }

    }
}