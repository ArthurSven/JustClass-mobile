package com.devapps.justclass.ui.screens

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.devapps.justclass.R
import com.devapps.justclass.Utils.Check
import com.devapps.justclass.Utils.LandingRoute
import com.devapps.justclass.data.auth.GoogleAuthClient
import com.devapps.justclass.ui.viewmodels.GoogleAuthViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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

    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    // Retrieve AuthViewModel with the context parameter
    val googleAuthViewModel: GoogleAuthViewModel = koinViewModel(parameters = { parametersOf(context) })

    val state by  googleAuthViewModel.state.collectAsStateWithLifecycle()

    val googleAuthClient by lazy {
        GoogleAuthClient(
            context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    val signInResult = googleAuthClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                   googleAuthViewModel.onSignInResult(signInResult)
                }
            }
        }
    )
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
            ElevatedButton(
                onClick = {
                    coroutineScope.launch {
                        val signInIntentSender = googleAuthClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.googlesignin),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier
                        .width(30.dp))
                    Text(
                        text = "Get Started",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 30.dp)
                    )
                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            Toast.makeText(
                                context,
                                "Sign in successful",
                                Toast.LENGTH_LONG
                            ).show()
                            justClassNavController.navigate(LandingRoute.route)
                            googleAuthViewModel.resetState()
                        }
                    }
                }
            }
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