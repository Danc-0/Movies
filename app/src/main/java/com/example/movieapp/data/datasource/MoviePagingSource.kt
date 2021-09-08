package com.example.movieapp.data.datasource

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.api.ApiService
import com.example.movieapp.data.datasource.ApiHelper
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.Result
import retrofit2.HttpException
import java.io.IOException
import java.security.Key

private const val MOVIE_STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val apiService: ApiService,
    private val apiKey: String
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

            val response = apiService.getMovieList(apiKey, nextPage)

            val responseList = mutableListOf<Result>()

            val data = response.results ?: emptyList()
            responseList.addAll(data)

            val previousPage = if (nextPage == 1)
                null else nextPage - 1

            LoadResult.Page(
                data = responseList,
                prevKey = null,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}