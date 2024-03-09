package com.example.patient.presntation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.usecase.details.DetailsPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: DetailsPatientByIdUseCase,
    state: SavedStateHandle
) :
    ViewModel() {

    private val _detailsStateFlow: MutableStateFlow<PatientRemoteModel?> =
        MutableStateFlow(null)
    val detailsStateFlow = _detailsStateFlow.asStateFlow()

    private val _detailsErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErrorStatFlow = _detailsErrorStateFlow.asStateFlow()

    private val _detailsLoadingStatFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStatFlow = _detailsLoadingStatFlow.asStateFlow()

    private val saveStateHandle = state
    init {
        details()
    }

    private fun details() {

        val id = saveStateHandle.get<String>("id")?:"-1"
        viewModelScope.launch(Dispatchers.IO) {
            _detailsLoadingStatFlow.emit(true)
            try {
                _detailsStateFlow.emit(getPatientByIdUseCase(id))
            } catch (e: Exception) {
                _detailsErrorStateFlow.emit(e)
            }
            _detailsLoadingStatFlow.emit(false)
        }
    }
}