package com.example.movieapp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResponse
import com.squareup.picasso.Picasso

class MovieAdapter (private val movieList: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            if (movie != null){
                binding.textViewRating.text = movie.vote_average.toString()

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movieList[position]
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}