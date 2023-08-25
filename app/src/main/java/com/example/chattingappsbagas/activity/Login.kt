package com.example.chattingappsbagas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingappsbagas.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        binding.btnSignIn.setOnClickListener {
            val Email = binding.emailEt.text.toString()
            val Pass = binding.passET.text.toString()
            if(Email.isNotEmpty() && Pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.emailEt.setText("")
                        binding.passET.setText("")
                        val intent = Intent(this, UserActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid Email or Password !", Toast.LENGTH_SHORT).show()
                    }
                }
            } else{
                Toast.makeText(this, "Field must be fill !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

