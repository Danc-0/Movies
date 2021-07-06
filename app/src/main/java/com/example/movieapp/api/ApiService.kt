package com.example.movieapp.api

import com.example.movieapp.model.MovieGenres
import com.example.movieapp.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Define Get request with query string parameter as page number
    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") api_key: String, @Query("page") page: Int) : MoviesResponse


//    https://api.themoviedb.org/3/genre/movie/list?api_key=8ed700250305de124bef08dbb686472a&language=en-US
    @GET("genre/movie/list")
    suspend fun getMovieGenreList(@Query("api_key") api_key: String, @Query("language") language: String) : MovieGenres

}