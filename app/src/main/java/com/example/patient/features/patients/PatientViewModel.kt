package com.example.patient.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patient.data.model.PatientRemoteModel
import com.example.patient.data.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val repository: PatientRepository) :
    ViewModel() {

    private val _patientSuccessStateFlow: MutableStateFlow<List<PatientRemoteModel>> =
        MutableStateFlow(
            emptyList()
        )
    val patientSuccessStateFlow: SharedFlow<List<PatientRemoteModel>> = _patientSuccessStateFlow

    private val _patientErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientErrorStatFlow: MutableStateFlow<Exception?> = _patientErrorStateFlow

    private val _progressBarStatFlow:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progressBarStatFlow: StateFlow<Boolean> = _progressBarStatFlow

        init {
        getPatient()
    }

    private fun getPatient() {
        viewModelScope.launch {
            _progressBarStatFlow.emit(true)
            try {
                val response = repository.getPatients()
                _patientSuccessStateFlow.emit(response)
            } catch (e: Exception) {
                _patientErrorStateFlow.emit(e)
            }
            _progressBarStatFlow.emit(false)
        }
    }
}