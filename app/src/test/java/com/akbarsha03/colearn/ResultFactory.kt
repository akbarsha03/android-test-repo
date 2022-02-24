package com.akbarsha03.colearn

import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.google.gson.Gson

class ResultFactory {
    fun createResult(default: String): ResponseData.CoverPhoto =
        Gson().fromJson(default, ResponseData.CoverPhoto::class.java)

    fun createSearchResult(default: String): SearchResponse =
        Gson().fromJson(default, SearchResponse::class.java)
}