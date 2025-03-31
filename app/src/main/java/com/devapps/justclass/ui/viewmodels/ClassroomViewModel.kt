package com.devapps.justclass.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devapps.justclass.data.model.Classroom
import com.devapps.justclass.data.model.ClassroomResponse
import com.devapps.justclass.data.model.StudentResponse
import com.devapps.justclass.data.model.UserData
import com.devapps.justclass.data.repository.ClassRoomRepository
import com.devapps.justclass.ui.state.CreateUiState
import com.devapps.justclass.ui.state.GetClassByIdUiState
import com.devapps.justclass.ui.state.GetStudentByIdUiState
import com.devapps.justclass.ui.state.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClassroomViewModel(
    val classRoomRepository: ClassRoomRepository,
    val userData: UserData
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateUiState>(CreateUiState.Idle)
    val uiState: StateFlow<CreateUiState> = _uiState.asStateFlow()

    private val _classrooms = MutableStateFlow<List<ClassroomResponse>>(emptyList())
    val classrooms : StateFlow<List<ClassroomResponse>> = _classrooms.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _classroom = MutableStateFlow<GetClassByIdUiState>(GetClassByIdUiState.Idle)
    val classroom: StateFlow<GetClassByIdUiState> = _classroom

    private val _createdBy = MutableStateFlow("")
    val createdBy: StateFlow<String> = _createdBy

    var classname by mutableStateOf("")
        private set

    var startdate by mutableStateOf("")
        private set

    var enddate by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var languageLevel by mutableStateOf("")
    private set


    fun updateClassname(input: String) {
        classname = input
    }

    fun updateStartDate(input: String) {
        startdate = input
    }

    fun updateEndDate(input: String) {
        enddate = input
    }

    fun updatePrice(input: String) {
        price = input
    }

    fun updateLanguageLevel(input: String) {
        languageLevel = input
    }

    fun setCreatedBy(userId: String) {
        _createdBy.value = userId
        viewModelScope.launch {
            getClassRoomsByTeacher(userId)
        }
    }

    fun createClassroom() {

        try {
            _uiState.value = CreateUiState.Loading
            val classroom = Classroom(
                classname,
                startdate,
                enddate,
                languageLevel,
                price,
                userData.username,
                userData.userId
            )

            viewModelScope.launch {
                val result = classRoomRepository.createClassRoom(classroom)

                when(result) {
                    is Response.Success -> {
                        _uiState.value = CreateUiState.Success
                        delay(2000)  // Give UI time to react
                        _uiState.value = CreateUiState.Idle
                    }

                    is Response.Error -> _uiState.value = CreateUiState.Error((result.error).toString())
                }
            }
        } catch (e: Exception) {
            _uiState.value = CreateUiState.Error("Could not create classroom: ${e.message}")
        }
    }

    fun getClassRoomsByTeacher(userId: String) {
        try {
            viewModelScope.launch {
                _isLoading.value = true
                val findClassrooms = classRoomRepository.getClassRoomByTeacher(userId)
                _classrooms.value = findClassrooms
            }
        } catch (e: Exception) {
            _uiState.value = CreateUiState.Error("Error fetching your classes: ${e.message}")
        } finally {
            _isLoading.value = false
        }
    }

    fun deleteClassroom(classroomid: String) {

        viewModelScope.launch {
            val result = classRoomRepository.deleteClassRoom(classroomid)

            when(result) {
                is Response.Success -> {
                    _uiState.value = CreateUiState.Success
                    delay(2000)
                    _uiState.value = CreateUiState.Idle
                }

                is Response.Error -> {
                    _uiState.value = CreateUiState.Error("Error: " + result.error)
                }
            }
        }

    }

}