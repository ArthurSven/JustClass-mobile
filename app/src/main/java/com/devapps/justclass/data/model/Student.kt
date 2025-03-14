package com.devapps.justclass.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val teacher: String?,
    val userid: String?
)

@Serializable
data class StudentResponse(
    val studentid: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val teacher: String?,
    val userid: String?
)
