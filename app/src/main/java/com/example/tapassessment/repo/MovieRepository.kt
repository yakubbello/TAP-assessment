package com.example.tapassessment.repo

import com.example.tapassessment.model.NewMovie
import com.example.tapassessment.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies()
}