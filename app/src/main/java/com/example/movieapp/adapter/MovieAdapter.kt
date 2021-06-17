package com.example.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val movieList: List<Result>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        val res = root.context.resources
        fun bind(movie: Result) {
            root.textViewRating.text = movie.vote_average.toString()
            val picasso = Picasso.get()
            Log.d("TAG", "bind: ${movie.poster_path}")
            val path = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            Log.d("TAG", "bind: $path")
            picasso.load(path).into(root.imageViewMovie)
//            root.imageViewMovie.setImageResource(movie.poster_path.toInt())

        }

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.movie_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(MovieViewHolder.LAYOUT, parent, false).let {
            MovieViewHolder(it)
        }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Result = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}