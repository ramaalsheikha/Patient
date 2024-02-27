package com.example.patient.features.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.patient.databinding.PatientFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientFragment:Fragment() {
    private lateinit var binding:PatientFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PatientFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}