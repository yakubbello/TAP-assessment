package com.example.tapassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tapassessment.adapter.MovieAdapter
import com.example.tapassessment.databinding.FragmentMovieBinding
import com.example.tapassessment.utils.Resource
import com.example.tapassessment.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentMovieBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            viewModel.movies.observe(viewLifecycleOwner) { result ->
                recycleView.apply {
                    if (result is Resource.Success || result.data!!.isNotEmpty()) {
                        adapter = MovieAdapter {
                            findNavController().navigate(MovieFragmentDirections.toDetailFragment(it))
                        }.apply {
                            submitList(result.data)
                        }
                    }
                }
                progressBar.isVisible = result is Resource.Loading && result.data!!.isNullOrEmpty()
                errorMessage.isVisible = result is Resource.Error && result.data!!.isNullOrEmpty()
                errorMessage.text = result.error?.localizedMessage
            }
        }
    }
}