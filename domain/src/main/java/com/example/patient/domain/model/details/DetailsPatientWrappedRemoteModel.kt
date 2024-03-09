package com.example.patient.domain.model.details

import com.example.patient.domain.model.patiens.PatientRemoteModel

data class DetailsPatientWrappedRemoteModel(
    val result:Int,
    val page:Int,
    val data:PatientRemoteModel,
)
