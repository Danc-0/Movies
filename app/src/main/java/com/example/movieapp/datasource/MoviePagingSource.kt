package com.example.movieapp.datasource

import androidx.paging.rxjava3.RxPagingSource
import com.example.movieapp.model.Movie
import io.reactivex.rxjava3.core.Single

class MoviePagingSource: RxPagingSource<Integer, Movie>() {
    override fun loadSingle(params: LoadParams<Integer>): Single<LoadResult<Integer, Movie>> {
        TODO("Not yet implemented")
        val page: Boolean = params.key != null

    }

}