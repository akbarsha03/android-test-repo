package com.akbarsha03.colearn.feature.pics.ui.adapters

import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse

interface OnItemSelectedListener {
    fun onItemSelected(urls: SearchResponse.SearchResult)
}