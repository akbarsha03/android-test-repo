package com.akbarsha03.colearn.feature.pics.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData


class PicsRecyclerAdapter(
    private val listener: OnItemSelectedListener,
) : PagingDataAdapter<ResponseData.CoverPhoto, PicsViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ResponseData.CoverPhoto>() {
            override fun areItemsTheSame(
                oldItem: ResponseData.CoverPhoto,
                newItem: ResponseData.CoverPhoto,
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ResponseData.CoverPhoto,
                newItem: ResponseData.CoverPhoto,
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PicsViewHolder.create(parent, listener)

    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
        val repoItem = getItem(position)
        repoItem?.let { holder.bind(repoItem) }
    }
}