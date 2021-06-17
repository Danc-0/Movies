package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MovieViewModel
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        viewModel.movieResponse.observe(viewLifecycleOwner, {

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {

                        val movieList: List<Result> = it.value.results
                        movieAdapter = MovieAdapter(movieList)
                        recyclerViewMovies.setHasFixedSize(true)

                        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

                        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                val viewType = movieAdapter.getItemViewType(position)
                                return  2
//                                else 2
                            }
                        }
                        recyclerViewMovies.layoutManager = gridLayoutManager
                        recyclerViewMovies.adapter = movieAdapter
                    }

                    movieAdapter.apply {true
                        notifyDataSetChanged()
                    }
                }

                is Resource.Failure -> {
                    val responseBody: ResponseBody? = it.errorBody
                    text_error.text = responseBody.toString()
                    text_error.isVisible = true

                }
            }

        })
    }
}