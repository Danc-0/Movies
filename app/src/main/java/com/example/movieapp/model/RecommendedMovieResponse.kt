package com.example.movieapp.model

data class RecommendedMovieResponse(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)