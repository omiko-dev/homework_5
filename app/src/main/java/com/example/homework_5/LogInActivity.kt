package com.example.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.homework_5.databinding.ActivityLogInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.ibBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.acbLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            logIn(email, password)
        }

    }

    private fun logIn(email: String, password: String){

        if(email.isNullOrBlank() || password.isNullOrBlank()){
            binding.tvFieldValidText.visibility = View.VISIBLE
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if(it.isSuccessful){
                binding.tvFieldValidText.visibility = View.INVISIBLE
            }else{
                binding.tvFieldValidText.visibility = View.VISIBLE
            }
        }
    }

}