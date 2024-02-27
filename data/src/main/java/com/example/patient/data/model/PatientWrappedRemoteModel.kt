package com.example.patient.data.model

data class PatientWrappedRemoteModel(
    val result:Int,
    val page:Int,
    val data:List<PatientRemoteModel>,
)
