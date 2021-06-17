package com.example.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val movieList: List<Result>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

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

    class MovieViewHolder(private val root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {

        val res = root.context.resources

        fun bind(movie: Result) {
            root.textViewRating.text = movie.vote_average.toString()
            val picasso = Picasso.get()
            val path = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            picasso.load(path).into(root.imageViewMovie)

            itemView.setOnClickListener(this)

        }

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.movie_item
        }

        override fun onClick(v: View?) {
            Toast.makeText(v?.context, "hoeloeloe", Toast.LENGTH_SHORT).show()
        }

    }

}