package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
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
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
@FragmentScoped
class MainFragment : Fragment(R.layout.fragment_main), MovieAdapter.OnItemClickListener{

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var movieAdapter: MovieAdapter
    var movieList: List<Result>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        viewModel.movieResponse.observe(viewLifecycleOwner, {

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {

                        movieList = it.value.results
                        movieAdapter = MovieAdapter(movieList!!, this@MainFragment)
                        recyclerViewMovies.setHasFixedSize(true)

                        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

                        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                val viewType = movieAdapter.getItemViewType(position)
                                return 2

                            }
                        }
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

    override fun movieItemClicked(position: Int) {
        val movieItem: Result? = movieList?.get(position)
        Toast.makeText(context, "Item is $movieItem", Toast.LENGTH_SHORT).show()
    }
}