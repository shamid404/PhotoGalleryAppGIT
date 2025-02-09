package com.example.photogalleryapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import coil.load

class FullscreenImageActivity : AppCompatActivity() { // Заменил ComponentActivity на AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)

        val imageView: ImageView = findViewById(R.id.fullscreenImageView)
        val imageUrl = intent.getStringExtra("IMAGE_URL")

        ViewCompat.setTransitionName(imageView, "image_transition")

        imageUrl?.let {
            imageView.load(it) {
                crossfade(true)
            }
        }

        imageView.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}
