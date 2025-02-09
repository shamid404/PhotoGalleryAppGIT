package com.example.photogalleryapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photogalleryapp.FullscreenImageActivity
import com.example.photogalleryapp.R
import com.example.photogalleryapp.model.UnsplashImage

class ImageAdapter(private var images: List<UnsplashImage>) :
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

        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 500 // 0.5 секунды
        }
        holder.itemView.startAnimation(fadeIn)

        holder.imageView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, FullscreenImageActivity::class.java).apply {
                putExtra("IMAGE_URL", imageUrl)
            }

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity,
                Pair(holder.imageView, "image_transition")
            )

            context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int = images.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateImages(newImages: List<UnsplashImage>) {
        images = newImages
        notifyDataSetChanged()
    }
}
