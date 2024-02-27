package com.example.patient.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patient.data.model.PatientRemoteModel
import com.example.patient.data.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val repository: PatientRepository) :ViewModel() {

    private val _patientSuccessLiveData:MutableLiveData<List<PatientRemoteModel>> = MutableLiveData()
    val patientSuccessLiveData: LiveData<List<PatientRemoteModel>> = _patientSuccessLiveData

    private val _patientErrorLiveData:MutableLiveData<Exception> = MutableLiveData()
    val patientErrorLiveData:LiveData<Exception> = _patientErrorLiveData

        init {
        getPatient()
    }

    private fun getPatient() {
        viewModelScope.launch {
            try {
                val response = repository.getPatients()
                _patientSuccessLiveData.postValue(response)
            }catch (e:Exception){
                _patientErrorLiveData.postValue(e)
            }
        }
    }
}