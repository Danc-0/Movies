package com.example.movieapp.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.api.ApiService
import com.example.movieapp.data.repository.MoviesRepository
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.Result
import kotlinx.coroutines.flow.Flow

internal class MoviesRemoteDataSourceImpl(
    private val apiService: ApiService,
    private val api_key: String
) : MoviesRepository {

    override fun getMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService, api_key)
            }
        ).flow
    }

    companion object{
        const val NETWORK_PAGE_SIZE = 50
    }
}
