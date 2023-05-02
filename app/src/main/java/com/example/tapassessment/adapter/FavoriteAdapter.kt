package com.example.tapassessment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tapassessment.R
import com.example.tapassessment.databinding.DisplayFavoriteBinding
import com.example.tapassessment.model.Movie
import com.example.tapassessment.utils.IMAGE_BASE_URL
import com.example.tapassessment.utils.convertDateFormat
import com.example.tapassessment.utils.rating

class FavoriteAdapter(
    private val itemSelected: (movie: Movie) -> Unit,
) : ListAdapter<Movie, FavoriteAdapter.FavoriteViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return DisplayFavoriteBinding.inflate(LayoutInflater.from(parent.context))
            .run { FavoriteViewHolder(this) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = currentList[position]

        holder.binding.apply {
            vote.text = "Votes: ${movie.voteCount}"
            title.text = movie.title
            year.text = convertDateFormat(movie.releaseDate)

            movieImage.load(IMAGE_BASE_URL + movie.posterPath) {
                error(R.drawable.ic_baseline_error_outline_24)
                placeholder(R.drawable.ic_launcher_background)
            }

            movieRating.text = holder.itemView.context.getString(R.string.rating, movie.voteAverage)
            progressBar.progress = rating(movie.voteAverage)

            movieImage.setOnClickListener {
                itemSelected.invoke(movie)
            }
        }
    }

    class FavoriteViewHolder(bind: DisplayFavoriteBinding) : RecyclerView.ViewHolder(bind.root) {
        val binding = bind
    }
}