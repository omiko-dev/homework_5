package com.example.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.homework_5.databinding.ActivityRegisterSecondBinding

class RegisterSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ibBack.setOnClickListener {
            goRegisterFirst()
        }


        binding.acbSignUp.setOnClickListener {
            if(checkUserName()){
                Toast.makeText(this@RegisterSecondActivity, "wow", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun goRegisterFirst(){
        val intent = Intent(this, RegisterFirstActivity::class.java)
        startActivity(intent)
    }

    private fun checkUserName(): Boolean{
        return binding.etUserName.text.toString().length > 6
    }
}