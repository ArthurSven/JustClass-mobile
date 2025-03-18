package com.devapps.justclass.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.devapps.justclass.data.model.UserData
import com.devapps.justclass.data.repository.ClassRoomRepository

class ClassroomViewModel(
    val classRoomRepository: ClassRoomRepository,
    val userData: UserData
) : ViewModel() {

}