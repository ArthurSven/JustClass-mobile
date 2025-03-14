package com.devapps.justclass.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Homework(
    val homework: String,
    val book: String,
    val page: String,
    val startdate: String,
    val duedate: String,
    val teacher: String,
    val userid: String
)

@Serializable
data class HomeworkResponse(
    val homeworkid: String,
    val homework: String,
    val book: String,
    val page: String,
    val startdate: String,
    val duedate: String,
    val teacher: String,
    val userid: String
)
