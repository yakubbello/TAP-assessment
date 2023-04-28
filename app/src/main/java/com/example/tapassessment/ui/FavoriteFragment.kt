package com.example.tapassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.tapassessment.R
import com.example.tapassessment.adapter.FavoriteAdapter
import com.example.tapassessment.databinding.FragmentFavoriteBinding
import com.example.tapassessment.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFavoriteBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteMovies()

        viewModel.favoriteMovies.observe(requireActivity()) { favoriteMovies ->
            binding.apply {

                recycleView.apply {
                    adapter = FavoriteAdapter { itemSelected ->
                        findNavController().navigate(
                            HomeFragmentDirections.toDetailFragment(
                                itemSelected
                            )
                        )
                    }.apply {
                        submitList(favoriteMovies)
                    }
                }
                emptyTv.isVisible = favoriteMovies.isNullOrEmpty()
            }
        }
    }
}