package com.example.photogalleryapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photogalleryapp.FullscreenImageActivity
import com.example.photogalleryapp.R
import com.example.photogalleryapp.model.UnsplashImage

class ImageAdapter(private val images: List<UnsplashImage>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = images[position].urls.regular

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.imageView)

        // Анимация появления
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 500 // 0.5 секунды
        holder.itemView.startAnimation(fadeIn)

        // Обработчик нажатия для открытия полноэкранного изображения
        holder.imageView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, FullscreenImageActivity::class.java)
            intent.putExtra("IMAGE_URL", imageUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = images.size
}
