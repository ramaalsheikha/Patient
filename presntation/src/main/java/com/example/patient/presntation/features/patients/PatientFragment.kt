package com.example.patient.presntation.features.patients

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
import com.example.patient.domain.model.delete.PatientDeleteResponseModel
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.presntation.R
import com.example.patient.presntation.databinding.PatientFragmentBinding
import com.example.patient.presntation.features.patients.adapter.PatientsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment :Fragment() {
    private lateinit var binding: PatientFragmentBinding
    val viewModel: PatientViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter
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

        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter = PatientsAdapter(::deletePatient)
        binding.rvPatient.adapter = adapter
    }

    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            try {
                findNavController().navigate(R.id.addPatientFragment)
            }catch (e:Exception){
                Log.e("TAGE",e.localizedMessage)
            }
        }
        binding.srPatient.setOnRefreshListener {

            lifecycleScope.launch {
                delay(3000)
                binding.srPatient.isRefreshing = false
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientStateFlow.collect(::onSuccessPatients)
        }
        lifecycleScope.launch {
            viewModel.getPatient()
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
        lifecycleScope.launch {
            viewModel.deletePatientLiveData.observe(viewLifecycleOwner,::onPatientDeleteSuccess)
        }
    }
    private fun onPatientDeleteSuccess(response:PatientDeleteResponseModel?){
        if (response!=null) {
            Toast.makeText(requireContext(), "${response.message}", Toast.LENGTH_SHORT).show()
            viewModel.getPatient()
        }

    }
    private fun onSuccessPatients(response:List<PatientRemoteModel>){
        if (response?.isNotEmpty() == true) {
            adapter.submitList(response)
        }
    }

    private fun deletePatient(id:String){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Are certain about patient deletion process")
            .setNegativeButton("No"){dialog,_->
                dialog.dismiss()
            }
            .setPositiveButton("Yes"){dialog , _ ->
                viewModel.deletePatient(id)
            }
            .show()
    }
}