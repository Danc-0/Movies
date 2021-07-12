package com.example.movieapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.ViewModel.AllCategoriesViewModel
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.utils.ReadError
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_all_categories.*
import okhttp3.ResponseBody

@AndroidEntryPoint
@FragmentScoped
class AllCategoriesFragment : Fragment(R.layout.fragment_all_categories) {

    private val TAG = "AllCategoriesFragment"
    private val viewModel by viewModels<AllCategoriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesGenre()
        tvGenre()

    }

    fun moviesGenre() {
        viewModel.getMoviesGenre()

        viewModel.movieGenreResponse.observe(viewLifecycleOwner, {

            when(it) {

                is Resource.Success -> {

                }

                is Resource.Failure -> {
                    errorResponse(it.errorBody, moviesError)
                }
            }

        })
    }

    fun tvGenre() {
        viewModel.getTVGenre()

        viewModel.myTVGenreResponse.observe(viewLifecycleOwner, {
            when(it) {

                is Resource.Success -> {
                    Log.d(TAG, "tvGenre: ${it.value.genres}")
                }

                is Resource.Failure -> {
                    errorResponse(it.errorBody, errorMsg)
                }

            }

        })
    }

    fun errorResponse(responseBody: ResponseBody?, view: TextView) {
        val error: String = ReadError().readError(responseBody)
        view.text = error
        view.visibility = View.VISIBLE

    }

}