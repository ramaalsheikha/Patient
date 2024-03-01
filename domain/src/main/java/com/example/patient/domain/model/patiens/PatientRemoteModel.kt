package com.example.patient.domain.model.patiens

data class PatientRemoteModel(
    val _id:String,
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
    val __v:Int,
    val tests:List<TestModel>,
    //local var
    var selected:Boolean = false

)