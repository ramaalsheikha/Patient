package com.example.patient.domain.model.base

data class BaseWrapped<T>(
    val result:Int,
    val page:Int,
    val data:T
)
