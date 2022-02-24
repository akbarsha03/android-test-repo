package com.akbarsha03.colearn.feature.pics.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akbarsha03.colearn.feature.pics.PhotosRepository.Companion.NETWORK_PAGE_SIZE
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.services.UnSplashApi
import com.akbarsha03.colearn.feature.pics.ui.QueryData
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1

class SearchPagingSource(
    private val service: UnSplashApi,
    private val queryData: QueryData,
) : PagingSource<Int, SearchResponse.SearchResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResponse.SearchResult> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val colorFilter = queryData.colorFilter

            Log.d("", "curl color is $colorFilter")

            val queryMap = HashMap<String, Any>()

            queryMap["query"] = queryData.query
            queryMap["page"] = position
            queryMap["per_page"] = params.loadSize

            if (!(queryData.colorFilter == null || queryData.colorFilter.equals("null"))) {
                queryMap["color"] = queryData.colorFilter.toString()
            }

            if (!(queryData.sortBy == null || queryData.sortBy.equals("null"))) {
                queryMap["order_by"] = queryData.sortBy.toString()
            }

            val response: SearchResponse = service.searchPhotos(queryMap)

            val nextKey = if (response.results?.isEmpty() != false) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            val page = LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
            page
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchResponse.SearchResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->

            val previousKey = state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            val nextKey = state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

            return@let previousKey ?: nextKey
        }
    }
}