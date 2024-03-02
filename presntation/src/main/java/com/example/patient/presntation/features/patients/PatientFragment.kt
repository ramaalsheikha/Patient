package com.example.patient.presntation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patient.presntation.databinding.PatientFragmentBinding
import com.example.patient.presntation.features.patients.adapter.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment() :Fragment() {
    private lateinit var binding: PatientFragmentBinding
    val viewModel: PatientViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PatientFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientSuccessStateFlow.collect { response ->
                if (response.isNotEmpty()) {
                    binding.rvPatient.adapter = PatientsAdapter(response)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.patientErrorStatFlow.collect{ e ->
                if (e!=null){
                    Log.e("TAG","Error $e")
                }

            }
        }
        lifecycleScope.launch {
            viewModel.progressBarStatFlow.collect{show ->
                binding.pbPatient.isVisible = show
            }
        }
    }
}