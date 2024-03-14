package com.lmh.minhhoang.dacs3

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lmh.minhhoang.dacs3.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var fb: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_register);
        fb= FirebaseAuth.getInstance()
        binding.buttonRegister.setOnClickListener {
            register();
        }
        binding.yesuser.setOnClickListener {
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent);
        }
    }
    private fun register() {
        val name = binding.name.text.toString()
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this@RegisterActivity, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show()
        } else {
            fb.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Đăng Kí Thành công", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorMessage = task.exception?.message ?: "Lỗi không xác định"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}