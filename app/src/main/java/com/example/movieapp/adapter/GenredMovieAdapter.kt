package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.ResultXX
import com.example.movieapp.views.GenresFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.genre_movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.imageViewMovie

class GenredMovieAdapter(
    private val movieList: List<ResultXX>
) :
    RecyclerView.Adapter<GenredMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.genre_movie_item, parent, false)
                .let {
                    MovieViewHolder(it)
                }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: ResultXX = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val res = itemView.context.resources
        var result :ResultXX? = null

        fun bind(movie: ResultXX) {
            result = movie
            val picasso = Picasso.get()
            val path = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            picasso.load(path).into(itemView.imageViewMovie)
            itemView.movieName.text = movie.original_title

        }
    }


}
