package com.example.loginandregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandregister.databinding.ActivityLogInBinding
import com.example.loginandregister.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    private lateinit var auth : FirebaseAuth

    override fun onStart() {
        super.onStart()

        // check if user already logged in
        val currentUser : FirebaseUser? = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val userName = binding.editTextText.text.toString()
            val password = binding.password.text.toString()

            if(userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else{
                auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Sign-In Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Sign-In Failed ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}