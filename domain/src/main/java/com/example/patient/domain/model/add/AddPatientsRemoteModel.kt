package com.example.patient.domain.model.add

import com.google.gson.annotations.SerializedName

data class AddPatientsRemoteModel (
    @SerializedName("_id")
    val id:String,
    val name:String,
    val address:String,
    val mobile:String,
    val email:String,
    val birthdate:String,
    val gender:String,
    val photo:String,
    val createdAt:String,
    val updatedAt:String,
    @SerializedName("__v")
    val v:Int,
)
