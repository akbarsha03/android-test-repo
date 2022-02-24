package com.akbarsha03.colearn

import androidx.paging.PagingSource
import com.akbarsha03.colearn.feature.pics.PhotosRepository
import com.akbarsha03.colearn.feature.pics.paging.PicsPagingSource
import com.akbarsha03.colearn.feature.pics.paging.SearchPagingSource
import com.akbarsha03.colearn.feature.pics.services.UnSplashApi
import com.akbarsha03.colearn.feature.pics.ui.QueryData
import com.akbarsha03.colearn.feature.pics.usecases.GetPhotosUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TestDomainUseCases : BaseUnitTest() {

    private lateinit var getPhotosUseCase: GetPhotosUseCase
    private lateinit var photosRepository: PhotosRepository
    private lateinit var unSplashApi: UnSplashApi

    private lateinit var source: PicsPagingSource

    private val resultFactory = ResultFactory()

    private val mockResults = listOf(
        resultFactory.createResult(first_mock_data),
        resultFactory.createResult(second_mock_data),
        resultFactory.createResult(third_mock_data)
    )

    private val mockSearchResult = resultFactory.createSearchResult(search_result)

    @Before
    fun setup() {
        unSplashApi = Mockito.mock(UnSplashApi::class.java)
        photosRepository = Mockito.mock(PhotosRepository::class.java)
        getPhotosUseCase = Mockito.mock(GetPhotosUseCase::class.java)
        source = PicsPagingSource(unSplashApi, "")
    }

    @Test
    fun `test if paging source is loading successful data`() = runBlockingTest {
        val id = "2423569"

        val apiCall = unSplashApi.getCollectionPhotosByID(
            id,
            1,
            1,
            null)

        whenever(apiCall).thenReturn(mockResults)

        val pagingSource = PicsPagingSource(unSplashApi, id)

        val expectedPage = PagingSource.LoadResult.Page(
            data = listOf(mockResults[0], mockResults[1]),
            prevKey = null,
            nextKey = mockResults[1].id
        )

        val loadedPage = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        val expectedData = expectedPage.data[0].id
        val loadedData = (loadedPage as PagingSource.LoadResult.Page).data[0].id

        assertEquals(expectedData, loadedData)
    }

    @Test
    fun `test if searching source is loading successful data`() = runBlockingTest {

        val queryMap = HashMap<String, Any>()

        queryMap["query"] = "sky"
        queryMap["page"] = 1
        queryMap["per_page"] = 1

        val apiCall = unSplashApi.searchPhotos(queryMap)

        whenever(apiCall).thenReturn(mockSearchResult)

        val queryData = QueryData("sky", null, null, null)

        val pagingSource = SearchPagingSource(unSplashApi, queryData)

        val expectedPage = PagingSource.LoadResult.Page(
            data = mockSearchResult.results ?: emptyList(),
            prevKey = null,
            nextKey = mockSearchResult.results?.get(0)
        )

        val loadedPage = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        val expectedData = expectedPage.data[0].id
        val loadedData = (loadedPage as PagingSource.LoadResult.Page).data[0].id

        assertEquals(expectedData, loadedData)
    }

    @Test
    fun `test if searching fails when invalid data provided`() = runBlockingTest {

        val queryMap = HashMap<String, Any>()

        val apiCall = unSplashApi.searchPhotos(queryMap)

        whenever(apiCall).thenReturn(mockSearchResult)

        val queryData = QueryData("sky", null, null, null)

        val pagingSource = SearchPagingSource(unSplashApi, queryData)

        val expectedPage = PagingSource.LoadResult.Page(
            data = mockSearchResult.results ?: emptyList(),
            prevKey = null,
            nextKey = mockSearchResult.results?.get(0)
        )

        val loadedPage = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

//        val expectedData = expectedPage.data[0].id
//        val loadedData = (loadedPage as PagingSource.LoadResult.Error)
//
//        assertEquals(expectedData, loadedData)
    }

    @After
    fun release() {
        Mockito.framework().clearInlineMocks()
    }
}
