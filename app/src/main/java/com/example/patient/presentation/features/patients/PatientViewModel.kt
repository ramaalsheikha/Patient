package com.example.patient.presentation.features.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.usecase.patiens.GetPatientsSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val getPatientsSortedByNameUseCase: GetPatientsSortedByNameUseCase) :
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
                _patientSuccessStateFlow.emit(getPatientsSortedByNameUseCase())
            } catch (e: Exception) {
                _patientErrorStateFlow.emit(e)
            }
            _progressBarStatFlow.emit(false)
        }
    }
}