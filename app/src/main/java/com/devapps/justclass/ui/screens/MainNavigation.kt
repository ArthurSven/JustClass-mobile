package com.devapps.justclass.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devapps.justclass.Utils.Check
import com.devapps.justclass.Utils.JustSplashRoute
import com.devapps.justclass.Utils.LandingRoute
import com.devapps.justclass.Utils.SignOutRoute
import com.devapps.justclass.Utils.WelcomeRoute
import com.devapps.justclass.data.auth.GoogleAuthClient
import com.devapps.justclass.ui.viewmodels.GoogleAuthViewModel
import com.google.android.gms.auth.api.identity.Identity
import org.koin.androidx.compose.koinViewModel

@Composable
fun JustClassNavigation() {

    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val googleAuthViewModel: GoogleAuthViewModel = koinViewModel()
    val state by googleAuthViewModel.state.collectAsState()

    val googleAuthClient by lazy {
        GoogleAuthClient(
            context,
            Identity.getSignInClient(context)
        )
    }

    val justClassMainNavController = rememberNavController()

    NavHost(justClassMainNavController, startDestination = JustSplashRoute.route) {
        composable(JustSplashRoute.route) {
            SplashScreen(justClassMainNavController)
        }
        composable(Check.route) {
            LaunchedEffect(key1 = Unit) {
                if(googleAuthClient.getSignedInUser() != null) {
                    if (state.isSignInSuccessful) {

                    }
                    justClassMainNavController.navigate(LandingRoute.route)
                } else {
                    justClassMainNavController.navigate(WelcomeRoute.route)
                }
            }
        }
        composable(WelcomeRoute.route) {
            WelcomeScreen(justClassMainNavController)
        }
        composable(LandingRoute.route) {
            MainLayout()
        }
        composable(SignOutRoute.route) {
            LaunchedEffect(Unit) {
                googleAuthClient.signOut()
                justClassMainNavController.navigate(WelcomeRoute.route)
            }
        }
    }

}