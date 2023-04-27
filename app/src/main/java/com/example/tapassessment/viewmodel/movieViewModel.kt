package com.example.tapassessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tapassessment.model.Movie
import com.example.tapassessment.repo.MovieRepository
import com.example.tapassessment.repo.MovieRepositoryImpl
import com.example.tapassessment.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    val movies = repo.getPopularMovies().asLiveData()

    fun addFavorite(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addFavoriteMovie(favoriteMovie = movie)
        }
    }
}