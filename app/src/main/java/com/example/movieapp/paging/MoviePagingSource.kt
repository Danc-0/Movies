package com.example.movieapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.BuildConfig
import com.example.movieapp.api.ApiService
import com.example.movieapp.model.Result
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val apiKey: String = BuildConfig.API_KEY
        val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

        return try {
            val response = apiService.getMovieList(apiKey, nextPage)

            val previousPage = if (nextPage == 1)
                null else nextPage - 1

            LoadResult.Page(
                data = response.results,
                prevKey = previousPage,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}