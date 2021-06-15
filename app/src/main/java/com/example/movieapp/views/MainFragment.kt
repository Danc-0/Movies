package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MovieViewModel
import com.example.movieapp.datasource.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        viewModel.movieResponse.observe(viewLifecycleOwner, {

            when(it){

                is Resource.Success -> {
                    lifecycleScope.launch {
                        it.value.total_results
                    }
                }

                is Resource.Failure -> {

                }
            }

        })
    }
}