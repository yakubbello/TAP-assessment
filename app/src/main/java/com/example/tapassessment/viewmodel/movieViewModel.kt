package com.example.tapassessment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tapassessment.model.Movie
import com.example.tapassessment.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _favoriteMovies : MutableLiveData<List<Movie>> = MutableLiveData()
    val favoriteMovies: LiveData<List<Movie>>
    get() = _favoriteMovies

    val movies = repo.getPopularMovies().asLiveData()

    fun addFavorite(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addFavoriteMovie(favoriteMovie = movie)
        }
    }

    fun getFavoriteMovies(){
        viewModelScope.launch {
            repo.getFavoriteMovies().collect{result ->
                _favoriteMovies.value = result
            }
        }
    }
}