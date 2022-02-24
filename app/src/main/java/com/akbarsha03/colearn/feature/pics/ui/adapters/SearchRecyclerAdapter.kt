package com.akbarsha03.colearn.feature.pics.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse

class SearchRecyclerAdapter(
    private val listener: OnItemSelectedListener,
) : PagingDataAdapter<SearchResponse.SearchResult, SearchViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR =
            object : DiffUtil.ItemCallback<SearchResponse.SearchResult>() {
                override fun areItemsTheSame(
                    oldItem: SearchResponse.SearchResult,
                    newItem: SearchResponse.SearchResult,
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: SearchResponse.SearchResult,
                    newItem: SearchResponse.SearchResult,
                ): Boolean =
                    oldItem == newItem
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder.create(parent, listener)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val repoItem = getItem(position)
        repoItem?.let { holder.bind(repoItem) }
    }
}