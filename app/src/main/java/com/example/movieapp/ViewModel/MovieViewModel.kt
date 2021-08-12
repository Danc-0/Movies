package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.data.repository.MoviesRepository
import com.example.movieapp.model.MovieGenres
import com.example.movieapp.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    val _myMovieResponse: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    val _myMovieGenreResponse: MutableLiveData<Resource<MovieGenres>> = MutableLiveData()

    val movieResponse: LiveData<Resource<MoviesResponse>>
        get() = _myMovieResponse

    val movieGenreResponse: LiveData<Resource<MovieGenres>>
        get() = _myMovieGenreResponse

//    fun getMovies() {
//        viewModelScope.launch {
//
//            _myMovieResponse.value = Resource.Loading
//
//            _myMovieResponse.value = repository.getMovies()
//        }
//    }

    fun getMovie(): Flow<PagingData<MoviesResponse>> {
        return repository.getMovies()
            .map { pagingData ->
                pagingData.map {
                    mapper.mapDomainMovieToUi(domainMovie = it)
                }
            }
            .cachedIn(viewModelScope)
    }

    fun getMoviesGenre() {
        viewModelScope.launch {

            _myMovieGenreResponse.value = Resource.Loading

            _myMovieGenreResponse.value = repository.getMovieGenre()
        }
    }

}