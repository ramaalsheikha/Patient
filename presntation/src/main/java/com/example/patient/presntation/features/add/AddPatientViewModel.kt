package com.example.patient.presntation.features.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val addPatientUseCase: AddPatientUseCase
):ViewModel() {

    private val _addPatientSuccessStateFlow: MutableStateFlow<AddPatientsRemoteModel?> =
        MutableStateFlow(null)
    val addPatientSuccessStateFlow = _addPatientSuccessStateFlow.asStateFlow()

    private val _addPatientErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val addPatientErrorStatFlow = _addPatientErrorStateFlow.asStateFlow()

    private val _progressBarStatFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progressBarStatFlow = _progressBarStatFlow.asStateFlow()

    fun addPatient(bodyAddPatientModel: BodyAddPatientModel){
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarStatFlow.emit(true)
            try {
                _addPatientSuccessStateFlow.emit(addPatientUseCase(bodyAddPatientModel))
            } catch (e: Exception) {
                _addPatientErrorStateFlow.emit(e)
            }
            _progressBarStatFlow.emit(false)
        }
    }
}