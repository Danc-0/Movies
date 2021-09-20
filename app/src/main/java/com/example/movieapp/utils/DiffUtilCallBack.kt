package com.example.movieapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.databinding.MovieItemBindingImpl
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.Result

class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem.id == newItem.id
                && oldItem.title == newItem.title
                && oldItem.original_title == newItem.original_title
    }
}