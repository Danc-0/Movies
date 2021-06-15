package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.datasource.Resource
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val _myMovieResponse: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    val movieResponse: LiveData<Resource<MovieResponse>>
        get() = _myMovieResponse

    fun getMovies() {
        viewModelScope.launch {

            _myMovieResponse.value = Resource.Loading

            _myMovieResponse.value = repository.getMovies()
        }
    }

}