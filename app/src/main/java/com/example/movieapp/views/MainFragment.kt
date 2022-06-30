package com.example.movieapp.views

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.util.*

@AndroidEntryPoint
@FragmentScoped
class MainFragment : Fragment(R.layout.fragment_main), MovieAdapter.OnItemClick,
    CategoryAdapter.CallBack {

    private val viewModel by viewModels<MovieViewModel>()
    private var movieAdapter: MovieAdapter? = null
    private lateinit var categoryAdapter: CategoryAdapter
    var movieCategory: List<Genre>? = null
    private var gridLayoutSpan = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        greetingsMessage()
        movieGenres()
        movieAdapter = MovieAdapter(this@MainFragment)
        movies()

        seeAll.setOnClickListener {
            findNavController(requireView()).navigate(R.id.to_allCategoriesFragment)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun movieItemClicked(singleMovie: Result) {
        val bundle = Bundle()
        bundle.putParcelable("MovieData", singleMovie)
        findNavController(requireView()).navigate(R.id.to_singleMovieFragment, bundle)
    }

    fun movieGenres() {
        viewModel.getMoviesGenre()

        viewModel.movieGenreResponse.observe(viewLifecycleOwner) {

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
                else -> {}
            }

        }
    }

    private fun movies() {
        val currentOrientation = resources.configuration.orientation
        gridLayoutSpan = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            3
        } else {
            2
        }

        recyclerViewMovies.apply {
            layoutManager = GridLayoutManager(context, gridLayoutSpan)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        lifecycleScope.launchWhenCreated {
            viewModel.getMovie().collectLatest {
                movieAdapter?.submitData(it)

            }
        }
    }

    private fun greetingsMessage() {
        val c: Calendar = Calendar.getInstance()
        val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
        var greeting: String? = null

        if (timeOfDay in 0..11) {
            greeting = "Good Morning,"
        } else if (timeOfDay in 12..15) {
            greeting = "Good Afternoon,"
        } else if (timeOfDay in 16..23) {
            greeting = "Good Evening,"
        }

        dayTag.text = greeting
    }

    override fun toGenreFrag() {
        findNavController(requireView()).navigate(R.id.to_genresFragment)
    }
}