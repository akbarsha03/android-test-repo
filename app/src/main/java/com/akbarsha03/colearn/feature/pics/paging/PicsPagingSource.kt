package com.akbarsha03.colearn.feature.pics.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akbarsha03.colearn.feature.pics.PhotosRepository.Companion.NETWORK_PAGE_SIZE
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.feature.pics.services.UnSplashApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PicsPagingSource(
    private val service: UnSplashApi,
    private val query: String,
) : PagingSource<Int, ResponseData.CoverPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseData.CoverPhoto> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = service.getCollectionPhotosByID(query, position, params.loadSize, null)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            Log.d("TAG", "load: #6.a ${response.size}")

            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseData.CoverPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->

            val previousKey = state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            val nextKey = state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

            return@let previousKey ?: nextKey
        }
    }
}