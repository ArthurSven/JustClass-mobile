package com.devapps.justclass.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devapps.justclass.data.auth.GoogleAuthClient
import com.devapps.justclass.data.auth.SignInState
import com.devapps.justclass.data.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GoogleAuthViewModel(googleAuthClient: GoogleAuthClient) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    fun setUser(userData: UserData) {
        _userData.value = userData
    }
}