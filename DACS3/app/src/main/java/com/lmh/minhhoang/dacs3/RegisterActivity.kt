package com.lmh.minhhoang.dacs3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lmh.minhhoang.dacs3.databinding.ActivityRegisterBinding
import com.lmh.minhhoang.dacs3.model.Users

class RegisterActivity : AppCompatActivity() {
    private lateinit var fb: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var user: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        fb = FirebaseAuth.getInstance()
        user = Users()

        binding.buttonRegister.setOnClickListener {
            register()
        }

        binding.yesuser.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register() {
        user.name = binding.name.text.toString()
        user.email = binding.username.text.toString()
        user.password = binding.password.text.toString()

        if (user.name.isEmpty() || user.email.isEmpty() || user.password.isEmpty()) {
            Toast.makeText(this@RegisterActivity, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show()
        } else {
            fb.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Firebase.firestore.collection("Users").document(fb.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            Toast.makeText(applicationContext, "Đăng Kí Thành công", Toast.LENGTH_LONG).show()
                        }
                } else {
                    val errorMessage = task.exception?.message ?: "Lỗi không xác định"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
