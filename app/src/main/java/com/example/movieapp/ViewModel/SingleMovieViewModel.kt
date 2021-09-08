package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.model.MovieGenres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleMovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val _myMovieGenreResponse: MutableLiveData<Resource<MovieGenres>> = MutableLiveData()


    val movieGenreResponse: LiveData<Resource<MovieGenres>>
        get() = _myMovieGenreResponse

    fun getMoviesGenre() {
        viewModelScope.launch {

            _myMovieGenreResponse.value = Resource.Loading

            _myMovieGenreResponse.value = repository.getMovieGenre("en-US")
        }
    }

}