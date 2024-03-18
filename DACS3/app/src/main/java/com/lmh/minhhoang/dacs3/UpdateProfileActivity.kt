package com.lmh.minhhoang.dacs3

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.lmh.minhhoang.dacs3.databinding.ActivityUpdateprofileBinding

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateprofileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_updateprofile);
        getDataUser()
    }
    private fun getDataUser() {
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            name?.let {
                binding.name.setText(name)
            }
            email.let {
                binding.email.setText(email)
            }
//            photoUrl?.let {
//                binding..setText(email)
//            }
        }
    }
}

