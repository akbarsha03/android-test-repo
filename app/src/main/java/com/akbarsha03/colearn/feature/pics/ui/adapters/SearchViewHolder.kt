package com.akbarsha03.colearn.feature.pics.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akbarsha03.colearn.database.PicsDao
import com.akbarsha03.colearn.databinding.PhotosListItemBinding
import com.akbarsha03.colearn.di.AppDatabaseEntryPoint
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.utils.imageloader.ImageLoader
import dagger.hilt.android.EntryPointAccessors

class SearchViewHolder(
    private val binding: PhotosListItemBinding,
    private val context: Context,
    private val listener: OnItemSelectedListener,
    private val picsDao: PicsDao,
) :
    RecyclerView.ViewHolder(binding.root) {

    private val tag = this.javaClass.simpleName

    fun bind(repoItem: SearchResponse.SearchResult) {

        Log.d(tag, "")

        val data = picsDao.findFavoriteByID(repoItem.id ?: "")

        Log.d(tag, "${data[0]}")

        ImageLoader.with(context).load(binding.imageItem, repoItem.urls?.small)

        binding.imageItem.setOnClickListener {
            val item = SearchResponse.SearchResult(repoItem.id, urls = repoItem.urls)
            listener.onItemSelected(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: OnItemSelectedListener): SearchViewHolder {
            val binding =
                PhotosListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val entryPoint = EntryPointAccessors.fromApplication(parent.context,
                AppDatabaseEntryPoint::class.java)
            return SearchViewHolder(binding,
                parent.context,
                listener,
                entryPoint.getDatabase.picsDao())
        }
    }
}