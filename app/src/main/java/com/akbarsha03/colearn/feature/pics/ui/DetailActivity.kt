package com.akbarsha03.colearn.feature.pics.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akbarsha03.colearn.databinding.ActivityDetailBinding
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.ui.viewmodels.DetailsViewModel
import com.akbarsha03.colearn.utils.imageloader.ImageLoader
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailsViewModel by viewModels()

    companion object {

        private const val KEY = "data"

        fun startActivity(context: Context, photosData: SearchResponse.SearchResult) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY, Gson().toJson(photosData))
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentData = intent.extras?.getString(KEY)
        val converted = Gson().fromJson(intentData, SearchResponse.SearchResult::class.java)
        ImageLoader.with(this).load(binding.detailImageView, converted.urls?.small)

        binding.addToFavorites.setOnClickListener {
            Log.d("unitag", "pointer 20 $intentData")

            if (viewModel != null) {
                Log.d("unitag", "pointer view model is fine")
                viewModel.addToFavorites(converted)
            } else
                Log.d("unitag", "pointer view model is null")

            Log.d("unitag", "pointer 21")
        }
    }
}