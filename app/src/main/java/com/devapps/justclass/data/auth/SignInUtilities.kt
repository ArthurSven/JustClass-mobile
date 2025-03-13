package com.devapps.justclass.data.auth

import com.devapps.justclass.data.model.UserData

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)