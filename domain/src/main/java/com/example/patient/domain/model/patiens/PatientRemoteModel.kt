package com.example.patient.domain.model.patiens

import com.google.gson.annotations.SerializedName

data class PatientRemoteModel(
    @SerializedName("_id")
    val id:String,
    val name:String,
    val address:String,
    val mobile:String,
    val email:String,
    val birthdate:String,
    val gender:String,
    val photo:String,
    val condition:String,
    val createdAt:String,
    val updatedAt:String,
    @SerializedName("__v")
    val v:Int,
    //local var
    var selected:Boolean = false
){
    fun getPatientInfo():String{
        return "Lives in $address , Email $email , Born on $birthdate"
    }
}
