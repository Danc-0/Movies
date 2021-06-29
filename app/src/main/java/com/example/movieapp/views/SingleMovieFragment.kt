package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_single_movie.*
import kotlinx.android.synthetic.main.movie_item.view.*

class SingleMovieFragment : Fragment(){

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

        val path = "https://image.tmdb.org/t/p/w500" + result?.poster_path
        Picasso.get().load(path).into(movieImage)

        movieName.text = result?.title
        synopsis.text = result?.overview
        rating.text = result?.vote_average.toString()

        val floatRate: Double? = result?.vote_average
        star.rating = floatRate?.toFloat()!!


    }

}