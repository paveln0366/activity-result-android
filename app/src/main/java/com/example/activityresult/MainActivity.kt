package com.example.activityresult

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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

        val contractUserName = ActivityResultContracts.StartActivityForResult()
        val launcherUserName = registerForActivityResult(contractUserName) {
            if (it.resultCode == RESULT_OK) {
                userNameTextView.text = it.data?.getStringExtra(UserNameActivity.EXTRA_USER_NAME)
            }
        }

        val contractImage = ActivityResultContracts.GetContent()
        val launcherImage = registerForActivityResult(contractImage) {
            imageFromGalleryImageView.setImageURI(it)
        }

        getUserNameButton.setOnClickListener {
            launcherUserName.launch(UserNameActivity.newIntent(this))
        }
        getImageButton.setOnClickListener {
            launcherImage.launch("image/*")
        }
    }

    private fun initViews() {
        getUserNameButton = findViewById(R.id.get_user_name_button)
        userNameTextView = findViewById(R.id.user_name_textview)
        getImageButton = findViewById(R.id.get_image_button)
        imageFromGalleryImageView = findViewById(R.id.image_from_gallery_imageview)
    }
}