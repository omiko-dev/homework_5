package com.example.homework_5

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.homework_5.databinding.ActivityRegisterFirstBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterFirstBinding
    private var emailRegex: Regex =  Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.ibBack.setOnClickListener {
            goToMain()
        }

        binding.acbNext.setOnClickListener {
            if(checkRegisterInfo()){
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                signIn(email, password)
                goToRegisterSecond()

            }
        }
    }

    private fun signIn(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goToRegisterSecond(){
        val intent = Intent(this, RegisterSecondActivity::class.java)
        startActivity(intent)
    }

    private fun checkRegisterInfo(): Boolean{
        binding.apply {
            tvEmailValidText.visibility = View.INVISIBLE
            tvPasswordValidText.visibility = View.INVISIBLE
            tvFieldValidText.visibility = View.INVISIBLE
        }

        if( // nullable validator
            binding.etEmail.text.isNullOrBlank() ||
            binding.etPassword.text.isNullOrBlank()
        ){
            binding.tvFieldValidText.visibility = View.VISIBLE
            return false
        }
        if( // email validator
            !emailRegex.containsMatchIn(binding.etEmail.text.toString())
        ){
            binding.tvEmailValidText.visibility = View.VISIBLE
            return false
        }
        if( //password validator
            binding.etPassword.text.toString().trim().contains(" ") ||
            binding.etPassword.text.toString().length < 6
        ){
            binding.tvPasswordValidText.visibility = View.VISIBLE
            return false
        }

        return  true
    }
}