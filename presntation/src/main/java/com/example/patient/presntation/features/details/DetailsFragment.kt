package com.example.patient.presntation.features.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patient.core.BaseFragment
import com.example.patient.presntation.R
import com.example.patient.presntation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment :BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details){
    private val viewModel:DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailsStateFlow.collect{ response ->
                Log.e("DetailsTag",response.toString())
                if (response!=null){
                    binding.model = response
                }
            }
        }
        lifecycleScope.launch {
            viewModel.detailsErrorStatFlow.collect{ response ->
                Log.e("ErrorTag",response.toString())
                if (response!=null){
                    Toast.makeText(requireContext(), "Details Error $response", Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.detailsLoadingStatFlow.collect{show ->
                binding.pbDetails.isVisible = show
            }
        }
    }
}