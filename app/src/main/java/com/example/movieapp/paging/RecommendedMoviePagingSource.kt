package com.example.movieapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.BuildConfig
import com.example.movieapp.api.ApiService
import com.example.movieapp.model.Languages
import com.example.movieapp.model.Result
import com.example.movieapp.model.ResultX
import javax.inject.Inject

class RecommendedMoviePagingSource @Inject constructor(
    private val apiService: ApiService,
    private val movieID: Int,
    private val language: String
) : PagingSource<Int, ResultX>() {

    override fun getRefreshKey(state: PagingState<Int, ResultX>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultX> {
        val apiKey: String = BuildConfig.API_KEY
        val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

        return try {
            val response = apiService.getRecommendedMovieList(movieID, apiKey, language, nextPage)

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