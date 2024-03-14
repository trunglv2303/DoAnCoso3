package com.lmh.minhhoang.dacs3;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lmh.minhhoang.dacs3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var fb: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        fb = FirebaseAuth.getInstance()
        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.notuser.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui Lòng Nhập Đầy Đủ Email hoặc Password", Toast.LENGTH_LONG).show()
            return
        }
        fb.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Đăng Nhập Thành công", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}
