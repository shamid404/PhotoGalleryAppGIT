package com.example.photogalleryapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.photogalleryapp.adapter.ImageAdapter
import com.example.photogalleryapp.network.NetworkManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        imageAdapter = ImageAdapter(emptyList())
        recyclerView.adapter = imageAdapter

        swipeRefreshLayout.setOnRefreshListener { fetchImages() }
        fetchImages()
    }

    private fun fetchImages() {
        swipeRefreshLayout.isRefreshing = true
        lifecycleScope.launch {
            try {
                val images = NetworkManager.api.fetchImages()
                imageAdapter.updateImages(images)
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки", Toast.LENGTH_SHORT).show()
                }
            } finally {
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}
