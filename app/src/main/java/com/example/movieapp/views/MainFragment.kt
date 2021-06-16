package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MovieViewModel
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.datasource.Resource
import com.example.movieapp.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        viewModel.movieResponse.observe(viewLifecycleOwner, {

            Log.d(TAG, "onViewCreated: ${it}")
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        it.value.total_results
                        Log.d(TAG, "onViewCreated: ${it.value.total_pages}")

                        val movieList: List<Result> = it.value.results
                        movieAdapter = MovieAdapter(movieList)
                        recyclerViewMovies.setHasFixedSize(true)
                        recyclerViewMovies.layoutManager = LinearLayoutManager(context)
                        recyclerViewMovies.adapter = movieAdapter
                    }

                    movieAdapter.apply {
                        notifyDataSetChanged()
                    }
                }

                is Resource.Failure -> {
                    Log.d(TAG, "onViewCreated: Failed")
                }
            }

        })
    }
}