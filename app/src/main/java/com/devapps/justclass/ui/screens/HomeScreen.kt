package com.devapps.justclass.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devapps.justclass.ui.composables.InfoCard
import com.devapps.justclass.ui.theme.aqua
import com.devapps.justclass.ui.theme.cream
import com.devapps.justclass.ui.theme.lilac
import com.devapps.justclass.ui.theme.peach

@Composable
fun HomeScreen() {
Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(rememberScrollState())
) {
    Spacer(modifier = Modifier
        .height(30.dp)
    )
    Text("Keep up with\n your classes",
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = 30.sp,
        color = Color.Black
        )
    Spacer(modifier = Modifier
        .height(20.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        InfoCard(stat = "3", title = "Classes created", color = cream)
        InfoCard(stat = "6", title = "Students enrolled", color = aqua)
        InfoCard(stat = "5", title = "Students paid", color = lilac)
        InfoCard(stat = "31", title = "Homeworks given", color = peach)
    }
}
}