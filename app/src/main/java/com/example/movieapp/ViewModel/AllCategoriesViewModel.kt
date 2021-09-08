package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.model.MovieGenres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCategoriesViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    val _myMovieGenreResponse: MutableLiveData<Resource<MovieGenres>> = MutableLiveData()


    val movieGenreResponse: LiveData<Resource<MovieGenres>>
        get() = _myMovieGenreResponse

    val _myTVResponse: MutableLiveData<Resource<MovieGenres>> = MutableLiveData()

    val myTVGenreResponse: MutableLiveData<Resource<MovieGenres>>
        get() = _myTVResponse

    fun getTVGenre() {
        viewModelScope.launch {

            _myTVResponse.value = Resource.Loading

            _myTVResponse.value = repository.getTVGenre("en-US")
        }
    }

    fun getMoviesGenre() {
        viewModelScope.launch {

            _myMovieGenreResponse.value = Resource.Loading

            _myMovieGenreResponse.value = repository.getMovieGenre("en-US")
        }
    }

}