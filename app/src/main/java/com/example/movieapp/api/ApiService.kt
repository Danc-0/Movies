package com.example.movieapp.api

import com.example.movieapp.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): MoviesResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenreList(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieGenres

    @GET("genre/tv/list")
    suspend fun getTVListGenreList(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieGenres

    @GET("movie/{movieID}/recommendations")
    suspend fun getRecommendedMovieList(
        @Path("movieID") movieID: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") pageNo: Int
    ): RecommendedMovieResponse

    @GET("configuration/languages")
    suspend fun getLanguageList(
        @Query("api_key") api_key: String
    ): Languages

    @GET("discover/movie")
    suspend fun getMoviesListByGenre(
        @Query("api_key") api_key: String,
        @Query("with_genres") genre_id: Int
    ): GenredMovies

}