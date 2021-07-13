package com.example.movieapp.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.model.Genre
import com.example.movieapp.model.RecommendedMovieResponse
import com.example.movieapp.model.Result
import com.example.movieapp.model.ResultX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.related_movie_item.view.*
import kotlinx.android.synthetic.main.related_movie_item.view.imageViewMovie
import kotlinx.android.synthetic.main.single_category_item.view.*

class RelatedMoviesAdapter(private val list: List<ResultX>, viewPager2: ViewPager2) : RecyclerView.Adapter<RelatedMoviesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.related_movie_item, parent, false).let {
                CategoryViewHolder(it)
            }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val movie: ResultX = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val res = itemView.context.resources

        fun bind(genre: ResultX){
            val path = "https://image.tmdb.org/t/p/w500" + genre.poster_path
            Picasso.get().load(path).into(itemView.imageViewMovie)
            itemView.movieName.text  = genre.title
//            itemView.tvMovieCategory.text  = genre.vote_average
        }
    }

}