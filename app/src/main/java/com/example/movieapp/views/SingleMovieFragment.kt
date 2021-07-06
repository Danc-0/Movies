package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MovieViewModel
import com.example.movieapp.ViewModel.SingleMovieViewModel
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Result
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.android.synthetic.main.fragment_single_movie.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FragmentScoped

class SingleMovieFragment : Fragment(){

    private val TAG = "SingleMovieFragment"
    private val viewModel by viewModels<SingleMovieViewModel>()
    private var movieGenre: List<Genre>? = null
    private var movieGenreIDs: List<Int>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val result: Result? = bundle!!.getParcelable("MovieData")

        val path = "https://image.tmdb.org/t/p/w500${result?.poster_path}"
        Picasso.get().load(path).into(movieImage)

        movieName.text = result?.original_title
        releaseDate.text = result?.release_date
        popularityNumber.text = result?.popularity.toString()
        synopsis.text = result?.overview
        rating.text = result?.vote_average.toString()

        val floatRate: Double? = result?.vote_average
        star.rating = floatRate?.toFloat()!!

        movieGenreIDs = result?.genre_ids
        Log.d(TAG, "onViewCreated: $movieGenreIDs")

        movieGenres()

    }

    fun movieGenres() {
        viewModel.getMoviesGenre()

        viewModel.movieGenreResponse.observe(viewLifecycleOwner, {

            when(it) {

                is Resource.Success -> {
                    lifecycleScope.launch {
                        movieGenre = it.value.genres

                        Log.d("TAG", "movieGenres: $movieGenre")
                    }

                }

                is  Resource.Failure -> {

                }

            }

        })
    }

}