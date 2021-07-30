package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Result
import kotlinx.android.synthetic.main.single_category_item.view.*

class ClassificationCategoryAdapter(private val list: List<Genre>, private val callBack: CallBack) : RecyclerView.Adapter<ClassificationCategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.classification_category_item, parent, false).let {
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

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val res = itemView.context.resources
        var result : Genre? = null

        fun bind(genre: Genre){
            result = genre
            itemView.setOnClickListener(this)
            itemView.tvMovieCategory.text  = genre.name
        }

        override fun onClick(v: View?) {
            callBack.sendId(result!!)
        }
    }

    interface CallBack {
        fun sendId(genre: Genre)
    }

}