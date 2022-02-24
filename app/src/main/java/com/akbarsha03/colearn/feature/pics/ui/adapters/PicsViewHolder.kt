package com.akbarsha03.colearn.feature.pics.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akbarsha03.colearn.databinding.PhotosListItemBinding
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.utils.imageloader.ImageLoader

class PicsViewHolder(
    private val binding: PhotosListItemBinding,
    private val context: Context,
    private val listener: OnItemSelectedListener,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(repoItem: ResponseData.CoverPhoto) {

        ImageLoader.with(context).load(binding.imageItem, repoItem.urls?.small)

        binding.imageItem.setOnClickListener {
            val item = SearchResponse.SearchResult(repoItem.id, urls = repoItem.urls)
            listener.onItemSelected(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: OnItemSelectedListener): PicsViewHolder {
            val binding =
                PhotosListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PicsViewHolder(binding, parent.context, listener)
        }
    }
}