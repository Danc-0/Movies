package com.example.movieapp.views

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.ViewModel.RecommendedMovieViewModel
import com.example.movieapp.adapter.CategoryAdapter
import com.example.movieapp.adapter.RelatedMoviesAdapter
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Result
import com.example.movieapp.model.ResultX
import com.example.movieapp.utils.ReadError
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_related_movies.*
import kotlinx.android.synthetic.main.fragment_single_movie.*
import kotlinx.android.synthetic.main.fragment_single_movie.movieName
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
@FragmentScoped
class RelatedMoviesFragment : Fragment(R.layout.fragment_related_movies) {

    private val TAG = "RelatedMoviesFragment"
    private val viewModel by viewModels<RecommendedMovieViewModel>()
    private lateinit var relatedAdapter: RelatedMoviesAdapter
    var relatedMovies: List<ResultX>? = null
    val sliderHandler: Handler = Handler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val result: Result? = bundle!!.getParcelable("Main Movie")

        movieName.text = result?.title
//        releaseDate.text = result?.release_date
        val path = "https://image.tmdb.org/t/p/w500${result?.poster_path}"
        Picasso.get().load(path).into(mainImage)

        recommendedMovies(result!!.id, 1)

    }

    private fun recommendedMovies(movieID: Int, pageNo: Int) {
        viewModel.getRecommendedMovies(movieID, pageNo)

        viewModel.recommendedMovieResponse.observe(viewLifecycleOwner, {
            
            when(it){
                
                is Resource.Success -> {
                    lifecycleScope.launch{
                        Log.d(TAG, "movieGenres: ${it.value}")

                        relatedMovies = it.value.results

                        relatedAdapter = RelatedMoviesAdapter(relatedMovies!!, rvRelatedMovies)

                        rvRelatedMovies.adapter = relatedAdapter
                        rvRelatedMovies.clipToPadding = false
                        rvRelatedMovies.clipChildren = false
                        rvRelatedMovies.offscreenPageLimit = 3
                        rvRelatedMovies.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

                        val compositePageTransformer = CompositePageTransformer()
                        compositePageTransformer.addTransformer(MarginPageTransformer(40))

                        val pageTrans = object : ViewPager2.PageTransformer {
                            override fun transformPage(page: View, position: Float) {

                                val r : Float = 1 - Math.abs(position)
                                page.scaleY = 0.85f + r * 0.15f
                            }
                        }

                        compositePageTransformer.addTransformer(pageTrans)
                        rvRelatedMovies.setPageTransformer(compositePageTransformer)

                        val pageClick = object : ViewPager2.OnPageChangeCallback() {
                            override fun onPageScrollStateChanged(state: Int) {
                                super.onPageScrollStateChanged(state)
                                sliderHandler.removeCallbacks(sliderRunnable)
                                sliderHandler.postDelayed(sliderRunnable, 3000)
                            }
                        }

                        rvRelatedMovies.registerOnPageChangeCallback(pageClick)
                    }

                }
                
                is Resource.Failure -> {
                    errorResponse(it.errorBody, errorMsg)
                }
            }
            
        })
    }

    val sliderRunnable : Runnable = Runnable(){
        run {
            rvRelatedMovies.setCurrentItem(rvRelatedMovies.currentItem + 1)
        }

    }


    fun errorResponse(responseBody: ResponseBody?, view: TextView) {
        val error: String = ReadError().readError(responseBody)
        view.text = error
        view.visibility = View.VISIBLE

    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}