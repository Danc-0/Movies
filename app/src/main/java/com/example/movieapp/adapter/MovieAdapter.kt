package com.example.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter (
    private val onItemClickListener: OnItemClick
) : PagingDataAdapter<Result, MovieAdapter.MovieViewHolder>(DataDifferentiator) {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View?) {
            itemView.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))}


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val result = getItem(position)

        if (result != null) {
            holder.itemView.tvMovieCategory.text = result.vote_average.toString()
        }
        val picasso = Picasso.get()
        val path = "https://image.tmdb.org/t/p/w500" + result!!.poster_path
        picasso.load(path).into(holder.itemView.imageViewMovie)

        holder.itemView.setOnClickListener {
            onItemClickListener.movieItemClicked(result)
        }

    }

    object DataDifferentiator : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClick {
        fun movieItemClicked(singleMovie: Result)
    }

}