package com.devapps.justclass.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devapps.justclass.data.model.Student
import com.devapps.justclass.data.model.StudentResponse
import com.devapps.justclass.data.model.UserData
import com.devapps.justclass.data.repository.StudentRepository
import com.devapps.justclass.ui.state.CreateUiState
import com.devapps.justclass.ui.state.GetStudentByIdUiState
import com.devapps.justclass.ui.state.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel(
    val studentRepository: StudentRepository,
    val userData: UserData
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateUiState>(CreateUiState.Idle)
    val uiState: StateFlow<CreateUiState> = _uiState.asStateFlow()

    private val _students = MutableStateFlow<List<StudentResponse>>(emptyList())
    val students : StateFlow<List<StudentResponse>> = _students.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _student = MutableStateFlow<GetStudentByIdUiState>(GetStudentByIdUiState.Idle)
    val pitch: StateFlow<GetStudentByIdUiState> = _student



    private val _createdBy = MutableStateFlow("")
    val createdBy: StateFlow<String> = _createdBy

    init {
        viewModelScope.launch {
            getStudentsByTeacher(_createdBy.value)
        }
    }

    fun setCreatedBy(userId: String) {
        _createdBy.value = userId
        viewModelScope.launch {
            getStudentsByTeacher(userId)
        }
    }

    var firstname by mutableStateOf("")
        private set

    var lastname by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    //methods to update form fields

    fun updateFirstname(input: String) {
        firstname = input
    }

    fun updateLastname(input: String) {
        lastname = input
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePhone(input: String) {
        phone = input
    }

    fun createStudent() {

        try {
            _uiState.value = CreateUiState.Loading
            val student = Student(
                firstname = firstname,
                lastname = lastname,
                email = email,
                phone = phone,
                teacher = userData.username,
                userid = userData.userId
            )

            viewModelScope.launch {
                val result = studentRepository.createStudent(student)

                when (result) {
                    is Response.Success -> _uiState.value = CreateUiState.Success
                    is Response.Error -> _uiState.value = CreateUiState.Error((result.error).toString())
                }
            }
        } catch (e: Exception) {
            _uiState.value = CreateUiState.Error("Could not create student: ${e.message}")
        }

    }

     fun getStudentsByTeacher(userid: String) {
        try {
            viewModelScope.launch {
                _isLoading.value = true
                val findStudents = studentRepository.getStudentsByTeacher(userid)
                _students.value = findStudents
            }

        } catch (e: Exception) {
            _uiState.value = CreateUiState.Error("Error fetching yourstudents: ${e.message}")
        } finally {
            _isLoading.value = false
        }
    }
}