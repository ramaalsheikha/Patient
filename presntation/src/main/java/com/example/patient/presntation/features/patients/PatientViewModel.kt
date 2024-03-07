package com.example.patient.presntation.features.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.usecase.patiens.GetPatientsSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val getPatientsSortedByNameUseCase: GetPatientsSortedByNameUseCase) :
    ViewModel() {

    private val _patientStateFlow: MutableStateFlow<List<PatientRemoteModel>> =
        MutableStateFlow(
            emptyList()
        )
    val patientStateFlow: SharedFlow<List<PatientRemoteModel>> = _patientStateFlow.asStateFlow()

    private val _patientErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientErrorStatFlow: StateFlow<Exception?> = _patientErrorStateFlow.asStateFlow()

    private val _progressBarStatFlow:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progressBarStatFlow: StateFlow<Boolean> = _progressBarStatFlow.asStateFlow()

        init {
        getPatient()
    }

    public fun getPatient() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarStatFlow.emit(true)
            try {
                _patientStateFlow.emit(getPatientsSortedByNameUseCase())
            } catch (e: Exception) {
                _patientErrorStateFlow.emit(e)
            }
            _progressBarStatFlow.emit(false)
        }
    }
}