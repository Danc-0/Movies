package com.example.movieapp.model

data class GenredMovies(
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)