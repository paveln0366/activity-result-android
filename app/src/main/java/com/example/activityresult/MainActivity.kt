package com.example.activityresult

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var getUserNameButton: Button
    private lateinit var userNameTextView: TextView
    private lateinit var getImageButton: Button
    private lateinit var imageFromGalleryImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        getUserNameButton.setOnClickListener {
            UserNameActivity.newIntent(this).apply {
                startActivityForResult(this, RC_USER_NAME)
            }
        }
        getImageButton.setOnClickListener {
            Intent(Intent.ACTION_PICK).apply {
                type = "image/*" // MIME types
                startActivityForResult(this, RC_IMAGE)
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_USER_NAME && resultCode == RESULT_OK) {
            val userName = data?.getStringExtra(UserNameActivity.EXTRA_USER_NAME) ?: ""
            userNameTextView.text = userName
        }
        if (requestCode == RC_IMAGE && resultCode == RESULT_OK) {
            val uri = data?.data
            imageFromGalleryImageView.setImageURI(uri)
        }
    }

    private fun initViews() {
        getUserNameButton = findViewById(R.id.get_user_name_button)
        userNameTextView = findViewById(R.id.user_name_textview)
        getImageButton = findViewById(R.id.get_image_button)
        imageFromGalleryImageView = findViewById(R.id.image_from_gallery_imageview)
    }

    companion object {

        private const val RC_USER_NAME = 100
        private const val RC_IMAGE = 101
    }
}