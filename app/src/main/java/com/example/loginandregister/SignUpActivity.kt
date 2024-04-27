package com.example.loginandregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandregister.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize firebase auth
        auth = FirebaseAuth.getInstance()

        binding.signInButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }

        binding.registerButton.setOnClickListener {

            // get text from edit text field
            val email = binding.name.text.toString()
            val userName = binding.editTextText.text.toString()
            val password = binding.Password.text.toString()
            val repeatPassword = binding.rePassword.text.toString()

            // check if any field is blank
            if(email.isEmpty() || userName.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            }else if(password != repeatPassword){
                Toast.makeText(this,"Repeat password must be same", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LogInActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this,"Registration Failed : ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}