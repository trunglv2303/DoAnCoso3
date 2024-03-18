package com.lmh.minhhoang.dacs3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.lmh.minhhoang.dacs3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logout.setOnClickListener{
            logout()
        }
        binding.updateProfile.setOnClickListener {
            updateProfile()
        }
    }
    private fun logout()
    {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun updateProfile()
    {
        val intent = Intent(this,UpdateProfileActivity::class.java)
        startActivity(intent)
    }
}