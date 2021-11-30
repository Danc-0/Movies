package com.example.movieapp.views

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MovieViewModel
import com.example.movieapp.adapter.CategoryAdapter
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Result
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.util.*

@AndroidEntryPoint
@FragmentScoped
class MainFragment : Fragment(R.layout.fragment_main), MovieAdapter.OnItemClickListener,
    CategoryAdapter.CallBack {

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    var movieList: List<Result>? = null
    var movieCategory: List<Genre>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieGenres()

        movies()

        greetingsMessage()

        seeAll.setOnClickListener {
            findNavController(requireView()).navigate(R.id.to_allCategoriesFragment)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun movieItemClicked(position: Int) {
        val movieItem: Result? = movieList?.get(position)
        val bundle = Bundle()
        bundle.putParcelable("MovieData", movieItem)
        findNavController(requireView()).navigate(R.id.to_singleMovieFragment, bundle)

    }

    fun movieGenres() {
        viewModel.getMoviesGenre()

        viewModel.movieGenreResponse.observe(viewLifecycleOwner, {

            when (it) {

                is Resource.Success -> {

                    lifecycleScope.launch {

                        movieCategory = it.value.genres

                        categoryAdapter = CategoryAdapter(movieCategory!!, this@MainFragment)
                        RvCategories.setHasFixedSize(true)

                        RvCategories.layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                        RvCategories.adapter = categoryAdapter
                    }

                    categoryAdapter.apply {
                        true
                        notifyDataSetChanged()
                    }

                }

                is Resource.Failure -> {

                }
            }

        })
    }

    private fun movies() {
        viewModel.getMovies()

        viewModel.movieResponse.observe(viewLifecycleOwner, {

            when (it) {

                is Resource.Success -> {

                    lifecycleScope.launch {
                        Log.d(TAG, "movieWDESGenres: ${it.value}")

                        movieList = it.value.results

                        movieAdapter = MovieAdapter(movieList!!, this@MainFragment)
                        recyclerViewMovies.setHasFixedSize(true)

                        recyclerViewMovies.layoutManager =
                            GridLayoutManager(context, 2)

                        recyclerViewMovies.adapter = movieAdapter
                    }

                    movieAdapter.apply {
                        true
                        notifyDataSetChanged()
                    }

                }

                is Resource.Failure -> {

                }
                else -> {}
            }

        })
    }

    private fun greetingsMessage() {
        val c: Calendar = Calendar.getInstance()
        val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
        var greeting: String? = null

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greeting = "Good Morning,"
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            greeting = "Good Afternoon,"
        } else if (timeOfDay >= 16 && timeOfDay < 24) {
            greeting = "Good Evening,"
        }

        dayTag.text = greeting
    }

    override fun toGenreFrag() {
        findNavController(requireView()).navigate(R.id.to_genresFragment)
    }
}