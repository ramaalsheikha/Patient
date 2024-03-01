package com.example.patient.domain.model.patiens

data class PatientWrappedRemoteModel(
    val result:Int,
    val page:Int,
    val data:List<PatientRemoteModel>,
)
