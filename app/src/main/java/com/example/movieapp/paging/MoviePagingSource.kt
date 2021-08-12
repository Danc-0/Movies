package com.example.movieapp.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.api.ApiService
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

    // Called to asynchronously fetch more data to be displayed
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key ?: MOVIE_STARTING_PAGE_INDEX

        return try {
            val response = apiService.getMovieList(
                apiKey,
                page = pageIndex
            )
            val movies = response.results
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / 1)
//                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == MOVIE_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}