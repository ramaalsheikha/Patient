package com.example.patient.presntation.features.add

import androidx.lifecycle.ViewModel
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val addPatientUseCase: AddPatientUseCase
):ViewModel() {

    private val _addPatientSuccessStateFlow: MutableStateFlow<List<PatientRemoteModel>> =
        MutableStateFlow(
            emptyList()
        )
    val addPatientSuccessStateFlow: SharedFlow<List<PatientRemoteModel>> = _addPatientSuccessStateFlow.asStateFlow()

    private val _addPatientErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val addPatientErrorStatFlow: StateFlow<Exception?> = _addPatientErrorStateFlow.asStateFlow()

    private val _progressBarStatFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progressBarStatFlow: StateFlow<Boolean> = _progressBarStatFlow.asStateFlow()
}