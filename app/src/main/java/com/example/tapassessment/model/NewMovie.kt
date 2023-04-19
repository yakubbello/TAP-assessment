package com.example.tapassessment.model

import com.google.gson.annotations.SerializedName

data class NewMovie(
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)