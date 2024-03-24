package com.lmh.minhhoang.dacs3

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.lmh.minhhoang.dacs3.databinding.ActivityUpdateprofileBinding
import com.lmh.minhhoang.dacs3.model.Users

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateprofileBinding
    lateinit var user:Users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_updateprofile);
        user= Users()
        getDataUser()
        binding.btncancle.setOnClickListener {
            val intent = Intent(this@UpdateProfileActivity,MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnupdate.setOnClickListener {
            update()
        }
        binding.plusImage.setOnClickListener{
            plusImage()
        }
    }
    private fun getDataUser() {
        Firebase.firestore.collection("Users").document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user: Users = it.toObject<Users>()!!
                binding.name.setText(user.name)
                binding.email.setText(user.email)
                binding.address.setText(user.address)
                binding.numberPhone.setText(user.numberPhone);
                Glide.with(this@UpdateProfileActivity)
                    .load(user.thumb)
                    .into(binding.profileUpdate)
            }
    }
    private fun plusImage()
    {

    }
    private fun update() {
        val user = Firebase.auth.currentUser
        val name = binding.name.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val phone = binding.numberPhone.text.toString().trim()
        val address = binding.address.text.toString().trim()

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }
        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@UpdateProfileActivity, "Profile updated successfully", Toast.LENGTH_LONG).show()

                    val userUpdate = Firebase.firestore.collection("Users").document(user.uid)
                    userUpdate.update("name", name)
                    userUpdate.update("email", email)
                    userUpdate.update("numberPhone", phone)
                    userUpdate.update("address", address)
                        .addOnSuccessListener {
                            Log.d(TAG, "OK")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "No Ok", e)
                        }
                } else {
                    Toast.makeText(this@UpdateProfileActivity, "Lỗi", Toast.LENGTH_LONG).show()
                }
            }
    }


}

