package com.devapps.justclass.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devapps.justclass.ui.theme.feintGrey
import com.devapps.justclass.ui.theme.textGrey

@Composable
fun ClassListScreen() {

    var search by rememberSaveable {
        mutableStateOf("")
    }

    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier
                .height(30.dp)
            )
            Text(
                text = "Class management",
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
                    Text(text = "Search for classroom")
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
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            IconButton(
                onClick = { /*TODO*/ },
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
fun CreateClassScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Text(
            text = "Create a Class",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Text(
            text = "Create your classes to track them",
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
                value = "",
                onValueChange = {

                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = feintGrey
                ),
                label = {
                    Text(text = "Classname")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Book,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = "",
                onValueChange = {

                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = feintGrey
                ),
                label = {
                    Text(text = "Start date")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "DD/MM/YYYY")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(value = "", onValueChange = {

            },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = feintGrey
                ),
                label = {
                    Text(text = "End date")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "DD/MM/YYYY")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(value = "",
                onValueChange = {

            },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = feintGrey
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "Level")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(value = "", onValueChange = {

            },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = feintGrey
                ),
                label = {
                    Text(text = "Price (MWK)")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Money,
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
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Text(text = "Create Class",
                    fontSize = 18.sp)
            }
        }

    }
}