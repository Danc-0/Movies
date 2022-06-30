package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.ResultX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.related_movie_item.view.*


class RelatedMoviesAdapter(private var callBack: CallBack) :
    PagingDataAdapter<ResultX, RelatedMoviesAdapter.CategoryViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.related_movie_item, parent, false)
                .let {
                    CategoryViewHolder(it)
                }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val res = itemView.context.resources

        fun bind(genre: ResultX) {
            val path = "https://image.tmdb.org/t/p/w500" + genre.poster_path
            Picasso.get().load(path).into(itemView.imageViewMovie)
            itemView.movieName.text = genre.title
            itemView.imgMoviePlayer.setOnClickListener {
                callBack.startDialog(itemView)
            }
        }


    }

    object DataDifferentiator : DiffUtil.ItemCallback<ResultX>() {

        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem == newItem
        }
    }


    interface CallBack {
        fun startDialog(view: View);
    }
}