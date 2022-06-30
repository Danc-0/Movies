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
import com.example.movieapp.model.*
import com.example.movieapp.paging.RecommendedMoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendedMovieViewModel @Inject constructor(
    private val apiService: ApiService,
    private val repository: MovieRepository) :
    ViewModel() {

    val _myRecommendedMovieResponse: MutableLiveData<Resource<RecommendedMovieResponse>> =
        MutableLiveData()

    val _myMovieGenreResponse: MutableLiveData<Resource<MovieGenres>> = MutableLiveData()

    val _movieLanguages: MutableLiveData<Resource<Languages>> = MutableLiveData()

    val recommendedMovieResponse: LiveData<Resource<RecommendedMovieResponse>>
        get() = _myRecommendedMovieResponse

    val movieGenreResponse: LiveData<Resource<MovieGenres>>
        get() = _myMovieGenreResponse

    val movieLanguages: LiveData<Resource<Languages>>
        get() = _movieLanguages

    fun recommendedMovies(movieID: Int, language: String) : Flow<PagingData<ResultX>> =
        Pager(PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        )) {
            RecommendedMoviePagingSource(apiService, movieID, language)
        }.flow.cachedIn(viewModelScope)

    fun getMoviesGenre() {
        viewModelScope.launch {

            _myMovieGenreResponse.value = Resource.Loading

            _myMovieGenreResponse.value = repository.getMovieGenre("en-US")
        }
    }

    fun getLanguages() {
        viewModelScope.launch {
            _movieLanguages.value = Resource.Loading

            _movieLanguages.value = repository.getLanguageList()
        }
    }

}