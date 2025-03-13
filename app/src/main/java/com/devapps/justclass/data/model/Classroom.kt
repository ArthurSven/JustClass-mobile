package com.devapps.justclass.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Classroom(
    val classname: String,
    val startdate: String,
    val enddate: String,
    val level: String,
    val price: String,
    val teacher: String
)

@Serializable
data class ClassroomResponse(
    val classroomid: String,
    val classname: String,
    val startdate: String,
    val enddate: String,
    val level: String,
    val price: String,
    val teacher: String
)
