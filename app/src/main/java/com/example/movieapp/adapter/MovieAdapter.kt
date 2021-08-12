package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    diffCallback: DiffUtil.ItemCallback<MoviesResponse>,
    private val movieList: List<Result>,
    private val onItemClickListener: OnItemClickListener
) : PagingDataAdapter<MoviesResponse, MovieAdapter.MovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false).let {
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

    inner class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val res = itemView.context.resources
        val result = Result

        fun bind(movie: Result) {
            itemView.tvMovieCategory.text = movie.vote_average.toString()
            val picasso = Picasso.get()
            val path = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            picasso.load(path).into(itemView.imageViewMovie)

        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                onItemClickListener.movieItemClicked(position)
            }
        }

    }

    interface OnItemClickListener {
        fun movieItemClicked(position: Int)
    }

}