package com.devapps.justclass.ui.composables

import androidx.activity.result.IntentSenderRequest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devapps.justclass.R
import com.devapps.justclass.data.auth.GoogleAuthClient
import com.devapps.justclass.data.model.StudentResponse
import com.devapps.justclass.ui.state.CreateUiState
import com.devapps.justclass.ui.theme.feintGrey
import com.devapps.justclass.ui.theme.peach
import com.devapps.justclass.ui.theme.textGrey
import com.devapps.justclass.ui.viewmodels.ClassroomViewModel
import com.devapps.justclass.ui.viewmodels.StudentViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


@Composable
fun InfoCard(
    stat: String,
    title: String,
    color: Color
) {
    ElevatedCard(
        onClick = { null },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp,
            focusedElevation = 8.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = stat,
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
                )
            Text(text = title,
                fontSize = 16.sp,
                color = Color.Black,
            )
        }
    }
}

@Composable
fun StudentCard(
    firstname: String,
    lastname: String,
    email: String,
    phone: String) {
    ElevatedCard(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(200.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            hoveredElevation = 8.dp,
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.no_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StudentCardDetail("Firstname", firstname)
                StudentCardDetail("Lastname", lastname)
                StudentCardDetail("Email", email)
                StudentCardDetail("Phone", phone)
            }
        }
    }
}

@Composable
fun ClassCard(
    classname: String,
    startDate: String,
    endDate: String,
    level: String,
    price: String) {
    ElevatedCard(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(IntrinsicSize.Max),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            hoveredElevation = 8.dp,
            defaultElevation = 8.dp
        )
    ) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Spacer(modifier = Modifier
                    .height(10.dp)
                )
                StudentCardDetail("Class", classname)
                StudentCardDetail("Level", level)
                StudentCardDetail("Price", price)
                StudentCardDetail("Start date", startDate)
                StudentCardDetail("End date", endDate)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
               IconButton(
                   onClick = { /*TODO*/ },
                   ) {
                   Icon(
                       imageVector = Icons.Default.MoreVert,
                       contentDescription = null,
                       tint = Color.Black
                   )
               } 
            }
        }
    }
}

@Composable
fun StudentCardDetail(title: String, info: String) {
    Row {
        Text(text = "${title}: ",
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(text = info,
            color = Color.Black
        )
    }
}



@Composable
fun StudentList(
    studentViewModel: StudentViewModel,
    selectCategory: Int
) {
   val uiState by studentViewModel.uiState.collectAsState()
    var studentToDelete by remember { mutableStateOf<StudentResponse?>(null) }
    val showDeleteDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    val googleClientAuth by lazy {
        GoogleAuthClient(
            context,
            Identity.getSignInClient(context)
        )
    }

    val students by studentViewModel.students.collectAsState()
    val isLoading by studentViewModel.isLoading.collectAsState()

    val userId = googleClientAuth.getSignedInUser()?.userId

    LaunchedEffect(userId) {
        if (userId != null) {
            studentViewModel.setCreatedBy(userId)
            studentViewModel.getStudentsByTeacher(userId)
        }
    }

    if (isLoading) {
        repeat(4) {
            ElevatedCard(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.LightGray
                ),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    hoveredElevation = 8.dp,
                    defaultElevation = 8.dp
                )
            ) {

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    } else {
        LazyColumn() {
            items(students) { student ->
                StudentCard(
                    firstname = student.firstname,
                    lastname = student.lastname,
                    email = student.email,
                    phone = student.phone
                )
            }
        }
    }
}

@Composable
fun ClassList(
    classroomViewModel: ClassroomViewModel,
) {
    val uiState by classroomViewModel.uiState.collectAsState()
    var studentToDelete by remember { mutableStateOf<StudentResponse?>(null) }
    val showDeleteDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    val googleClientAuth by lazy {
        GoogleAuthClient(
            context,
            Identity.getSignInClient(context)
        )
    }

    val classrooms by classroomViewModel.classrooms.collectAsState()
    val isLoading by classroomViewModel.isLoading.collectAsState()

    val userId = googleClientAuth.getSignedInUser()?.userId

    LaunchedEffect(userId) {
        if (userId != null) {
            classroomViewModel.setCreatedBy(userId)
            classroomViewModel.getClassRoomsByTeacher(userId)
        }
    }

    if (isLoading) {
        repeat(4) {
            ElevatedCard(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.LightGray
                ),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    hoveredElevation = 8.dp,
                    defaultElevation = 8.dp
                )
            ) {

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    } else {
        LazyColumn() {
            items(classrooms) { classRoom ->
                ClassCard(
                    classname = classRoom.classname,
                    startDate = classRoom.startdate,
                    endDate = classRoom.enddate,
                    level = classRoom.level,
                    price = classRoom.price
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassLevelDropDown(
    languageLevelList: List<String>,
    selectedLevel: String,
    onLevelSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) } // State for dropdown expansion

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
    ) {
        OutlinedTextField(
            value = selectedLevel,
            onValueChange = {
            },
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = feintGrey
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = "Level")
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        // Dropdown menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languageLevelList.forEach { level ->
                // Dropdown menu item
                DropdownMenuItem(
                    text = { Text(text = level) },
                    onClick = {
                        onLevelSelected(level) // Update selected category
                        expanded = false // Collapse the dropdown
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}



@Composable
@Preview(showBackground = true)
fun UtilityPreview() {
}