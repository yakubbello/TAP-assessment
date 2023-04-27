package com.example.tapassessment.repo

import com.example.tapassessment.model.NewMovie
import com.example.tapassessment.network.NetworkService
import com.example.tapassessment.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: NetworkService
) : MovieRepository {

    override fun getPopularMovies(){}

//    = flow {
//        emit(Resource.Loading())
//        try {
//            val response = service.getPopularMovies()
//            emit(Resource.Success(response.body()))
//        } catch (throwable: Throwable) {
//            emit(Resource.Error(throwable))
//        }
//    }
}