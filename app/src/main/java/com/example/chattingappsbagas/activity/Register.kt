package com.example.chattingappsbagas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingappsbagas.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val userName = binding.userNameEt.text.toString()
            val Email = binding.emailEt.text.toString()
            val Pass = binding.passET.text.toString()
            val Conf_Pass = binding.confirmPassEt.text.toString()



            if (Email.isNotEmpty() && Pass.isNotEmpty() && Conf_Pass.isNotEmpty()) {
                if (Pass.length < 6) {
                    Toast.makeText(this, "Password min 6 character !", Toast.LENGTH_SHORT).show()
                }
                if (Pass == Conf_Pass) {
                    registerUser(userName, Email, Pass)
                } else {
                    Toast.makeText(this, "Password not Matching !", Toast.LENGTH_SHORT).show()
                    binding.confirmPassEt.requestFocus()
                    return@setOnClickListener
                }
            } else {
                Toast.makeText(this, "Field must be fill !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


        }
    }

    private fun registerUser(userName: String, Email: String, Pass: String) {

        auth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser? = auth.currentUser
                val userId: String = user!!.uid
                dbReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)
                val hashMap: HashMap<String, String> = HashMap()
                hashMap.put("userId", userId)
                hashMap.put("userName", userName)
                hashMap.put("profileImage", "")
                dbReference.setValue(hashMap).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        binding.userNameEt.setText("")
                        binding.emailEt.setText("")
                        binding.passET.setText("")
                        binding.confirmPassEt.setText("")
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}


