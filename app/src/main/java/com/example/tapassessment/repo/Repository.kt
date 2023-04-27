package com.example.tapassessment.repo

import androidx.room.withTransaction
import com.example.tapassessment.db.AppDatabase
import com.example.tapassessment.db.MovieDao
import com.example.tapassessment.model.Movie
import com.example.tapassessment.network.NetworkService
import com.example.tapassessment.utils.Resource
import com.example.tapassessment.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: NetworkService,
    private val db: AppDatabase,
    private val movieDao: MovieDao
    ) {

    fun getPopularMovies(): Flow<Resource<List<Movie>>> = networkBoundResource(
        query = {
            movieDao.getAllMovies()
        }, fetch = {
            service.getPopularMovies()
        }, saveFetchResult = {data ->
            db.withTransaction {
                movieDao.deleteAllAlbumPhoto()
                movieDao.insertMovies(data.movies)
            }

        }
    )


    fun getFavoriteMovies():Flow<List<Movie>>{
        return movieDao.getFavoriteMovies()
    }

    suspend fun addFavoriteMovie(favoriteMovie: Movie){
        movieDao.addFavoriteMovie(favoriteMovie)
    }

    fun removeFromFavorite(movie: Movie){
        movieDao.removeFavorite(movie)
    }
}