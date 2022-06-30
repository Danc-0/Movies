package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.api.ApiService
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.model.MovieGenres
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.Result
import com.example.movieapp.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val apiService: ApiService,
) : ViewModel() {


    val _myMovieResponse: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    val _myMovieGenreResponse: MutableLiveData<Resource<MovieGenres>> = MutableLiveData()

    val movieResponse: LiveData<Resource<MoviesResponse>>
        get() = _myMovieResponse

    val movieGenreResponse: LiveData<Resource<MovieGenres>>
        get() = _myMovieGenreResponse


    fun getMovie(): Flow<PagingData<Result>> =
        Pager(
            PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false,
            )
        ) {

            MoviePagingSource(
                apiService
            )
        }.flow.cachedIn(viewModelScope)

    fun getMoviesGenre() {
        viewModelScope.launch {

            _myMovieGenreResponse.value = Resource.Loading

            _myMovieGenreResponse.value = repository.getMovieGenre("en-US")
        }
    }
}