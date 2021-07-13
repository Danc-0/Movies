package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.model.MovieGenres
import com.example.movieapp.model.RecommendedMovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendedMovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val _myRecommendedMovieResponse: MutableLiveData<Resource<RecommendedMovieResponse>> = MutableLiveData()

    val recommendedMovieResponse: LiveData<Resource<RecommendedMovieResponse>>
        get() = _myRecommendedMovieResponse

    fun getRecommendedMovies(movieID: Int, pageNo: Int) {
        viewModelScope.launch {

            _myRecommendedMovieResponse.value = Resource.Loading

            _myRecommendedMovieResponse.value = repository.getRecommendedMovies(movieID, pageNo)
        }
    }

}