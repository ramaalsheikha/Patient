package com.example.patient.presntation.features.add

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
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.presntation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : Fragment() {
    private lateinit var binding: FragmentAddPatientBinding
    private val viewModel: AddPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            if (infoIsValid()) {
                val body = getInfoPatient()
                viewModel.addPatient(body)
            }
        }
    }

    private fun getInfoPatient(): BodyAddPatientModel {
        return BodyAddPatientModel(
            binding.tieFullName.text.toString(),
            binding.tieAddress.text.toString(),
            binding.tieGender.text.toString(),
            binding.tieBirthdate.text.toString(),
            binding.tieMobile.text.toString(),
            binding.tieEmail.text.toString(),
        )
    }

    private fun infoIsValid(): Boolean {
        var isValid = true

        if (binding.tieFullName.text?.isEmpty() == true) {
            isValid = false
            binding.tieFullName.error = "Name is Empty"
        }else{
           binding.tiFullName.error = null
        }
        if (binding.tieAddress.text?.isEmpty() == true) {
            isValid = false
            binding.tieAddress.error = "Address is Empty"
        }else{
            binding.tiAddress.error = null
        }
        if (binding.tieBirthdate.text?.isEmpty() == true) {
            isValid = false
            binding.tieBirthdate.error = "Birthdate is Empty"
        }else{
            binding.tiBirthdate.error = null
        }
        if (binding.tieEmail.text?.isEmpty() == true) {
            isValid = false
            binding.tieEmail.error = "Email is Empty"
        }else{
            binding.tiEmail.error = null
        }
        if (binding.tieGender.text?.isEmpty() == true) {
            isValid = false
            binding.tieGender.error = "Gender is Empty"
        }else{
            binding.tiGender.error = null
        }
        if (binding.tieMobile.text?.isEmpty() == true) {
            isValid = false
            binding.tieMobile.error = "Mobile is Empty"
        }else{
            binding.tiMobile.error = null
        }
        return isValid
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.addPatientSuccessStateFlow.collect { response ->
                if (response != null) {
                    Toast.makeText(
                        requireContext(),
                        "Patient added successfully : \n name ${response.name}  \n createdAt : ${response.createdAt} ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.progressBarStatFlow.collect { show ->
                binding.pbAddPatient.isVisible = show
            }
        }

        lifecycleScope.launch {
            viewModel.addPatientErrorStatFlow.collect { e ->
                if (e != null) {
                    Log.d("TAG00", e.toString())
                }
            }
        }
    }
}