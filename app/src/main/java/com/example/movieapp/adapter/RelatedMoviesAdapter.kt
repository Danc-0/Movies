package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.model.ResultX
import com.example.movieapp.views.VideoPlayerDialogFrag
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.related_movie_item.view.*
import kotlinx.android.synthetic.main.related_movie_item.view.imageViewMovie
import kotlinx.android.synthetic.main.single_category_item.view.*


class RelatedMoviesAdapter(private var list: List<ResultX>, private val viewPager2: ViewPager2, private var callBack: CallBack) :
    RecyclerView.Adapter<RelatedMoviesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.related_movie_item, parent, false)
                .let {
                    CategoryViewHolder(it)
                }

        return layoutInflater
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val movie: ResultX = list[position]
        holder.bind(movie)

//        if (position == list.size - 2){
//            viewPager2.post(runnable)
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val res = itemView.context.resources

        fun bind(genre: ResultX) {
            val path = "https://image.tmdb.org/t/p/w500" + genre.poster_path
            Picasso.get().load(path).into(itemView.imageViewMovie)
            itemView.movieName.text = genre.title
            itemView.imgMoviePlayer.setOnClickListener {
                callBack.startDialog(genre)
            }
        }


    }

    interface CallBack {
        fun startDialog(genre: ResultX);
    }

//    val runnable: Runnable = Runnable() {
//        run {
////            list.
//            notifyDataSetChanged()
//        }
//    }
}