package com.example.tapassessment.repo

import androidx.room.withTransaction
import com.example.tapassessment.db.AppDatabase
import com.example.tapassessment.db.MovieDao
import com.example.tapassessment.model.Movie
import com.example.tapassessment.model.NewMovie
import com.example.tapassessment.network.NetworkService
import com.example.tapassessment.utils.Resource
import com.example.tapassessment.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: NetworkService,
    private val db: AppDatabase,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getPopularMovies():Flow<Resource<List<Movie>>> = networkBoundResource(
        query = {
            movieDao.getAllMovies()
        }, fetch = {
            service.getPopularMovies()
        }, saveFetchResult = {data ->
            db.withTransaction {
                movieDao.deleteAllAlbumPhoto()
                movieDao.insertMovies(data.movies)
            }
        }, shouldFetch = {userInDB ->
            userInDB.isEmpty()
        }
    )

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieDao.getFavoriteMovies()
    }

    override suspend fun addFavoriteMovie(favoriteMovie: Movie) {
        movieDao.addFavoriteMovie(favoriteMovie)
    }

}