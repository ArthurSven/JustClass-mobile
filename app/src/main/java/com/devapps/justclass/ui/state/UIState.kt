package com.devapps.justclass.ui.state

import com.devapps.justclass.data.model.ClassroomResponse
import com.devapps.justclass.data.model.HomeworkResponse
import com.devapps.justclass.data.model.PaymentResponse
import com.devapps.justclass.data.model.StudentResponse

sealed class CreateUiState {
    object Idle : CreateUiState()
    object Loading : CreateUiState()
    object Success : CreateUiState()
    data class Error(val message: String) : CreateUiState()
}

sealed class Response {
    object Success : Response()
    class Error(val error: Exception) : Response()
}

sealed class GetStudentByIdUiState {
    data object Idle : GetStudentByIdUiState()
    data object Loading : GetStudentByIdUiState()
    data class Success(val student: StudentResponse) : GetStudentByIdUiState()
    data class Error(val message: String) : GetStudentByIdUiState()
}

sealed class GetClassByIdUiState {
    data object Idle : GetClassByIdUiState()
    data object Loading : GetClassByIdUiState()
    data class Success(val classRoom: ClassroomResponse) : GetClassByIdUiState()
    data class Error(val message: String) : GetClassByIdUiState()
}

sealed class GetPaymentByIdUiState {
    data object Idle : GetPaymentByIdUiState()
    data object Loading : GetPaymentByIdUiState()
    data class Success(val payment: PaymentResponse) : GetPaymentByIdUiState()
    data class Error(val message: String) : GetPaymentByIdUiState()
}

sealed class GetHomeworkByIdUiState {
    data object Idle : GetHomeworkByIdUiState()
    data object Loading : GetHomeworkByIdUiState()
    data class Success(val homework: HomeworkResponse) : GetHomeworkByIdUiState()
    data class Error(val message: String) : GetHomeworkByIdUiState()
}