package com.devapps.justclass.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devapps.justclass.Utils.AddStudentRoute
import com.devapps.justclass.data.model.UserData
import com.devapps.justclass.ui.composables.StudentList
import com.devapps.justclass.ui.state.CreateUiState
import com.devapps.justclass.ui.theme.feintGrey
import com.devapps.justclass.ui.theme.textGrey
import com.devapps.justclass.ui.viewmodels.StudentViewModel
import kotlinx.coroutines.launch


@Composable
fun StudentListScreen(
    justClassAuthNavController: NavController
) {

    var search by rememberSaveable {
        mutableStateOf("")
    }

Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.White)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier
            .height(30.dp)
        )
        Text(
            text = "Student management",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier
            .height(10.dp)
        )
        OutlinedTextField(
            value = search,
            onValueChange = {
                search == it
            },
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                focusedTextColor = Color.Black,
                focusedContainerColor = feintGrey,
                unfocusedContainerColor = feintGrey,
                unfocusedLeadingIconColor = Color.DarkGray,
                focusedLeadingIconColor = Color.DarkGray,
                unfocusedBorderColor = feintGrey,
                focusedBorderColor = feintGrey
            ),
            placeholder = {
                Text(text = "Search for student")
            },
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = Color.DarkGray)
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
        )
        StudentList(

        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        IconButton(
            onClick = {
                justClassAuthNavController.navigate(AddStudentRoute.route)
            },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Black
            ),
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                tint = Color.White)
        }
    }
}
}

@Composable
fun CreateStudentScreen(
    userData: UserData?,
    studentViewModel: StudentViewModel
) {

    val uiState by studentViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext

    var insertionAttempted by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Text(
                text = "Create a Student",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                text = "Create your students profiles to keep track on them",
                color = textGrey,
                fontSize = 16.sp
            )
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = studentViewModel.firstname,
                    onValueChange = {
                        studentViewModel.updateFirstname(it)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = feintGrey,
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    label = {
                        Text(text = "Firstname")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = studentViewModel.lastname,
                    onValueChange = {
                        studentViewModel.updateLastname(it)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = feintGrey,
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    label = {
                        Text(text = "Lastname")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = studentViewModel.email,
                    onValueChange = {
                        studentViewModel.updateEmail(it)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = feintGrey,
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    label = {
                        Text(text = "Email")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = studentViewModel.phone,
                    onValueChange = {
                        studentViewModel.updatePhone(it)
                    },
                    label = {
                        Text(text = "Phone")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = feintGrey,
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier
                    .height(20.dp)
                )
                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            studentViewModel.createStudent()
                        }
                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                ) {
                    Text(text = "Create student",
                        fontSize = 18.sp)
                }
        }

        }

        LaunchedEffect(uiState) {
            if (uiState is CreateUiState.Success) {
                Toast.makeText(
                    context,
                    "Student was successfully created",
                    Toast.LENGTH_LONG
                ).show()
                // Reset the form fields
                studentViewModel.updateFirstname("")
                studentViewModel.updateLastname("")
                studentViewModel.updateEmail("")
                studentViewModel.updatePhone("")
            }
        }

        when(uiState) {
            CreateUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is CreateUiState.Error -> {
                val errorMessage = (uiState as CreateUiState.Error).message
                Toast.makeText(context,
                    "Student was not created: ${errorMessage}", Toast.LENGTH_LONG)
                    .show()
                Log.e("Student create error", errorMessage)
            }

            CreateUiState.Idle -> {

            }

            else -> {

            }
        }
        
    }
}

@Composable
@Preview(showBackground = true)
fun StudentScreenPreview() {

}