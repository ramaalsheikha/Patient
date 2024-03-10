package com.example.patient.presntation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patient.core.BaseFragment
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
class PatientFragment :BaseFragment<PatientFragmentBinding>(R.layout.patient_fragment){
    val viewModel: PatientViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter = PatientsAdapter(::deletePatient,::onClickItem)
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
        Log.i("DeleteTag", response!!.statusCode.toString())
        if (response!=null) {
            Toast.makeText(requireContext(), "${response.status}", Toast.LENGTH_SHORT).show()
            viewModel.getPatient()
        }

    }
    private fun onSuccessPatients(response:List<PatientRemoteModel>){
        if (response.isNotEmpty()) {
            adapter.submitList(response)
        }
    }

    private fun deletePatient(id:String){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Are you certain about patient deletion process")
            .setNegativeButton("No"){dialog,_->
                dialog.dismiss()
            }
            .setPositiveButton("Yes"){dialog , _ ->
                viewModel.deletePatient(id)
                dialog.dismiss()
            }
            .show()
    }
    private fun onClickItem(id:String){
       findNavController().navigate(R.id.detailsFragment, bundleOf("id" to id))
    }
}