package com.example.movieapp.views

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ViewModel.SingleGenreViewModel
import com.example.movieapp.adapter.*
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.Genre
import com.example.movieapp.model.ResultXX
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_genres.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped
class GenresFragment : Fragment(R.layout.fragment_genres), ClassificationCategoryAdapter.CallBack{

    private val TAG = "GenresFragment"
    private val viewModel by viewModels<SingleGenreViewModel>()
    private lateinit var movieAdapter: GenredMovieAdapter
    private lateinit var singleCategory: ClassificationCategoryAdapter
    var movieList: List<ResultXX>? = null
    var movieCategory: List<Genre>? = null
        var lastItem: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies(18)
        movieGenres()
    }

    fun repeatCall() {

        object : CountDownTimer(20000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                lastItem++
            }

            override fun onFinish() {
                repeatCall()//again call your method
            }

        }.start()

    }

    fun movieGenres(){
        viewModel.getMoviesGenre()

        viewModel.movieByGenreResponse.observe(viewLifecycleOwner) {

            when (it) {

                is Resource.Success -> {

                    lifecycleScope.launch {

                        movieCategory = it.value.genres

                        singleCategory =
                            ClassificationCategoryAdapter(movieCategory!!, this@GenresFragment)
                        firstCategories.setHasFixedSize(true)

                        firstCategories.layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                        firstCategories.adapter = singleCategory


                    }

                    singleCategory.apply {
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

    fun getMovies(genre_id: Int) {
        viewModel.getMoviesByGenre(genre_id)

        viewModel.movieGenreResponse.observe(viewLifecycleOwner) {
            when (it) {

                is Resource.Success -> {
                    lifecycleScope.launch {

                        movieList = it.value.results
                        movieAdapter = GenredMovieAdapter(movieList!!)
                        genreBased.setHasFixedSize(true)

                        val gridLayoutManager = GridLayoutManager(requireContext(), 4)

                        genreBased.layoutManager = gridLayoutManager
                        genreBased.adapter = movieAdapter
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
        }
    }

    override fun sendId(genre: Genre) {
        getMovies(genre.id)
    }

}