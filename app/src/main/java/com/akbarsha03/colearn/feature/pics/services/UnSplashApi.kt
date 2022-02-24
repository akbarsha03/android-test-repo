package com.akbarsha03.colearn.feature.pics.services

import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UnSplashApi {
    @GET("/collections/{id}/photos")
    suspend fun getCollectionPhotosByID(
        @Path("id") id: String,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 8,
        @Query("orientation") photoOrientation: String?,
    ): List<ResponseData.CoverPhoto>

    @GET("/search/photos")
    suspend fun searchPhotos(@QueryMap params: Map<@JvmSuppressWildcards String, @JvmSuppressWildcards Any>): SearchResponse
}

