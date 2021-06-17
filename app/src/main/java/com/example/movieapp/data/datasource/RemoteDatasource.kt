package com.example.movieapp.data.datasource

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDatasource @Inject constructor(){

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()

    fun <Api> buildRetrofit(
        api: Class<Api>
    ) : Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

//    val API_SERVICE: ApiService = getRetrofit().create(ApiService::class.java)

}