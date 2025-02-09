package com.example.photogalleryapp

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import coil.load

class FullscreenImageActivity : ComponentActivity() { // Изменил с AppCompatActivity на ComponentActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)

        val imageView: ImageView = findViewById(R.id.fullscreenImageView)
        val imageUrl = intent.getStringExtra("IMAGE_URL")

        imageUrl?.let {
            imageView.load(it) {
                crossfade(true)
            }
        }

        imageView.setOnClickListener {
            finish() // Закрываем активити
        }
    }
}

