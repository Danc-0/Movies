package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import kotlinx.android.synthetic.main.genre_list.view.*

internal class MovieGenreAdapter(private var moviesList: List<String>) : RecyclerView.Adapter<MovieGenreAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.genre_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    internal inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(genre: String){
            view.name.text = genre
        }
    }


}