package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Result
import kotlinx.android.synthetic.main.single_category_item.view.*

class CategoryAdapter(private val list: List<Genre>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.single_category_item, parent, false).let {
                CategoryViewHolder(it)
            }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val movie: Genre = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val res = itemView.context.resources
        val result = Result

        fun bind(genre: Genre){
            itemView.tvMovieCategory.text  = genre.name
        }
    }

}