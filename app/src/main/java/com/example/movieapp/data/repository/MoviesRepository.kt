package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.model.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<Result>>
}