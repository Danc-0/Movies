package com.example.movieapp.model

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)