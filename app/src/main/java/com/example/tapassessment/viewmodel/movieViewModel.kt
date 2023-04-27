package com.example.tapassessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tapassessment.repo.MovieRepository
import com.example.tapassessment.repo.MovieRepositoryImpl
import com.example.tapassessment.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repo: Repository
) : ViewModel() {
    val movies = repo.getPopularMovies().asLiveData()
}