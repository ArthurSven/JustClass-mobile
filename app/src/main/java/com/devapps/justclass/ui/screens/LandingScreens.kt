package com.devapps.justclass.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devapps.justclass.R
import com.devapps.justclass.Utils.Check
import com.devapps.justclass.ui.composables.SocialLoginButton
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(justClassNavController: NavController) {

    LaunchedEffect(Unit) {
        delay(2000)
        justClassNavController.navigate(Check.route)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.justspeaklogo2),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
        )
    }
}

@Composable
fun WelcomeScreen(justClassNavController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier
                .height(300.dp))
            Text(
                text = "Welcome",
                color = Color.White,
                fontSize = 36.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier
                .height(5.dp)
            )
            Text(
                text = "Manage your students and class with ease",
                color = Color.White,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier
                .height(300.dp)
            )
            SocialLoginButton()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLandingScreens() {

}