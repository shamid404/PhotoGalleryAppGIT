package com.example.photogalleryapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photogalleryapp.adapter.ImageAdapter
import com.example.photogalleryapp.network.NetworkManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        fetchImages()
    }

    private fun fetchImages() {
        lifecycleScope.launch {
            try {
                val images = NetworkManager.api.fetchImages()
                recyclerView.adapter = ImageAdapter(images)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Ошибка загрузки", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
