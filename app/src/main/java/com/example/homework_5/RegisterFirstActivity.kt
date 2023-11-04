package com.example.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.homework_5.databinding.ActivityRegisterFirstBinding

class RegisterFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterFirstBinding
    private var emailRegex: Regex =  Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private var passwordRegex: Regex = Regex("^\\S*\$")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibBack.setOnClickListener {
            goMain()
        }


        binding.acbNext.setOnClickListener {
            if(checkRegisterInfo()){

                goRegisterSecond()
            }
        }
    }

    private fun goMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goRegisterSecond(){
        val intent = Intent(this, RegisterSecondActivity::class.java)
        startActivity(intent)
    }

    private fun checkRegisterInfo(): Boolean{
        if( // nullable validator
            binding.etEmail.text.isNullOrBlank() ||
            binding.etPassword.text.isNullOrBlank()
        ){
            return false
        }
        if( // email validator
            !emailRegex.containsMatchIn(binding.etEmail.text.toString())
        ){
            return false
        }
        if( //password validator
            binding.etPassword.text.toString().trim().contains(" ") ||
            binding.etPassword.text.toString().length < 6
        ){
            return false
        }

        return  true
    }
}