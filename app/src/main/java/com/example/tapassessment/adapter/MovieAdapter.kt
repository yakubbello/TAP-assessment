package com.example.tapassessment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tapassessment.R
import com.example.tapassessment.databinding.DisplayMovieBinding
import com.example.tapassessment.model.Movie
import com.example.tapassessment.utils.IMAGE_BASE_URL
import com.example.tapassessment.utils.convertDateFormat

class MovieAdapter(
    private val itemSelected: (movie: Movie) -> Unit,
    private val favoriteMovie: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return DisplayMovieBinding.inflate(LayoutInflater.from(parent.context))
            .run { MovieViewHolder(this) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = currentList[position]

        holder.binding.apply {
            vote.text = "Votes: ${movie.voteCount}"
            title.text = movie.title
            year.text = convertDateFormat(movie.releaseDate)

            movieImage.load(IMAGE_BASE_URL + movie.posterPath) {
                error(R.drawable.ic_baseline_error_outline_24)
                placeholder(R.drawable.ic_launcher_background)
            }

            movieImage.setOnClickListener {
                itemSelected.invoke(movie)
            }

            if (movie.isFavorite)
                favorite.setImageResource(R.drawable.baseline_favorite)
            else
                favorite.setImageResource(R.drawable.favorite)

            favorite.setOnClickListener {
                if (movie.isFavorite) {
                    movie.isFavorite = false
                    favorite.setImageResource(R.drawable.favorite)
                    favoriteMovie.invoke(movie)
                } else {
                    movie.isFavorite = true
                    favorite.setImageResource(R.drawable.baseline_favorite)
                    favoriteMovie.invoke(movie)
                }
            }
        }
    }


    class MovieViewHolder(bind: DisplayMovieBinding) : RecyclerView.ViewHolder(bind.root) {
        val binding = bind
    }
}