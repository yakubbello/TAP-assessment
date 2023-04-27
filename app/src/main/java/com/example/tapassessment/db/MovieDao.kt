package com.example.tapassessment.db

import androidx.room.*
import com.example.tapassessment.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM popularMovies")
    fun getAllMovies():Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)


    @Query(value = "DELETE from popularMovies")
    suspend fun deleteAllAlbumPhoto()

    @Update
    suspend fun addFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM popularMovies WHERE isFavorite = 1 ")
    fun getFavoriteMovies(): Flow<List<Movie>>

    @Delete
    fun removeFavorite(movie:Movie)
}