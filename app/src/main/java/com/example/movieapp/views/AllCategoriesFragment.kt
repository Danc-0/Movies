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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.ViewModel.AllCategoriesViewModel
import com.example.movieapp.adapter.CategoryAdapter
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.Genre
import com.example.movieapp.utils.ReadError
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_all_categories.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
@FragmentScoped
class AllCategoriesFragment : Fragment(R.layout.fragment_all_categories), CategoryAdapter.CallBack {

    private val TAG = "AllCategoriesFragment"
    private val viewModel by viewModels<AllCategoriesViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter
    var movieCategory: List<Genre>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesGenre()


    }

    fun moviesGenre() {
        viewModel.getMoviesGenre()

        viewModel.movieGenreResponse.observe(viewLifecycleOwner, {

            when (it) {

                is Resource.Success -> {

                    lifecycleScope.launch {
                        movieCategory = it.value.genres

                        categoryAdapter = CategoryAdapter(movieCategory!!, this@AllCategoriesFragment)

                        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

                        rvGenreMovies.layoutManager = gridLayoutManager
                        rvGenreMovies.adapter = categoryAdapter
                    }

                        categoryAdapter.apply {
                            true
                            notifyDataSetChanged()
                    }

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
            when (it) {

                is Resource.Success -> {

                    lifecycleScope.launch {


                    }

                    categoryAdapter.apply {
                        true
                        notifyDataSetChanged()
                    }
                }

                is Resource.Failure -> {

                }

            }

        })
    }

    fun errorResponse(responseBody: ResponseBody?, view: TextView) {
        val error: String = ReadError().readError(responseBody)
        view.text = error
        view.visibility = View.VISIBLE

    }

    override fun toGenreFrag() {

    }

}