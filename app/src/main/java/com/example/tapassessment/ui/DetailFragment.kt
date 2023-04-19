package com.example.tapassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.tapassessment.R
import com.example.tapassessment.databinding.FragmentDetailBinding
import com.example.tapassessment.model.Movie
import com.example.tapassessment.utils.IMAGE_BASE_URL
import com.example.tapassessment.utils.convertDateFormat


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val navController = findNavController()
//        binding.toolbar.setupWithNavController(navController)

        val movie = DetailFragmentArgs.fromBundle(requireArguments()).movie
        setupUi(movie)
    }
    private fun setupUi(movie: Movie) {
        binding.apply {
            displayImage.load(IMAGE_BASE_URL + movie.movieImage) {
                error(R.drawable.ic_baseline_error_outline_24)
                placeholder(R.drawable.ic_launcher_background)
            }
            movieTitle.text = movie.title
            movieYear.text = convertDateFormat(movie.releaseDate)
            movieVotes.text = getString(R.string.votes, movie.voteCount)
            movieRating.text = getString(R.string.rating, movie.voteAverage)
            movieOverView.text = movie.overview
            progressBar.progress = ((movie.voteAverage/10)*100).toInt()
        }
    }
}