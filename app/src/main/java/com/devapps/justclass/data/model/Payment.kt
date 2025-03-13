package com.devapps.justclass.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Payment(
    val student : String,
    val classroom: String,
    val level: String,
    val date: String,
    val amount: String,
    val teacher: String
)

@Serializable
data class PaymentResponse(
    val paymentid : String,
    val student : String,
    val classroom: String,
    val level: String,
    val date: String,
    val amount: String,
    val teacher: String
)
