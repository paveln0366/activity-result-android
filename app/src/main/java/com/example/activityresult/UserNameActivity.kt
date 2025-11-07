package com.example.activityresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserNameActivity : AppCompatActivity() {

    private lateinit var userNameEditText: EditText
    private lateinit var saveUserNameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_name)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.user_name)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        saveUserNameButton.setOnClickListener {
            val userName = userNameEditText.text.trim().toString()
            saveUserName(userName)
            finish()
        }
    }

    private fun initViews() {
        userNameEditText = findViewById(R.id.user_name_edittext)
        saveUserNameButton = findViewById(R.id.save_user_name_button)
    }

    private fun saveUserName(userName: String) {

    }

    companion object {

        fun newIntent(context: Context) = Intent(context, UserNameActivity::class.java)
    }
}