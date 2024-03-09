package com.example.patient.domain.usecase.details

import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.model.delete.PatientDeleteResponseModel
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.repo.patients.PatientRepository
import javax.inject.Inject

class DetailsPatientByIdUseCase @Inject constructor(private val repository: PatientRepository) {

    suspend operator fun invoke(id:String): PatientRemoteModel{
       return repository.detailsPatientById(id)
    }
}