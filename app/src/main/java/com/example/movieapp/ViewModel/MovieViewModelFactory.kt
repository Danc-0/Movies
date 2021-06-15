package com.example.movieapp.ViewModel

import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}