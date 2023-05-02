package com.example.tapassessment.viewmodel

import androidx.lifecycle.*
import com.example.tapassessment.model.Movie
import com.example.tapassessment.repo.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieRepositoryImpl
) : ViewModel() {

    private val _favoriteMovies : MutableLiveData<List<Movie>> = MutableLiveData()
    val favoriteMovies: LiveData<List<Movie>>
    get() = _favoriteMovies

    val movies = repo.getPopularMovies().asLiveData()

    fun addFavorite(id: Int, isFavorite:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addFavoriteMovie(id = id, isFavorite = isFavorite)
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