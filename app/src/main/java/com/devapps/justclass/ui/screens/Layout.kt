package com.devapps.justclass.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Class
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devapps.justclass.Utils.ClassroomRoute
import com.devapps.justclass.Utils.HomeRoute
import com.devapps.justclass.Utils.PaymentRoute
import com.devapps.justclass.Utils.StudentRoute
import com.devapps.justclass.ui.composables.BottomNavItem
import com.devapps.justclass.ui.theme.feintGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(

) {
    val context = LocalContext.current.applicationContext
    val showMenu = remember { mutableStateOf(false) }
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    BackHandler {
        //justClassNavController.popBackStack(Signup.route, false)
    }

    val bottomNavItems = listOf(
        BottomNavItem(
            "home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            HomeRoute.route
        ),
        BottomNavItem(
            "class",
            Icons.Filled.Class,
            Icons.Outlined.Class,
            ClassroomRoute.route
        ),
        BottomNavItem(
            "students",
            Icons.Filled.Group,
            Icons.Outlined.Group,
            StudentRoute.route
            ),
        BottomNavItem(
            "h/work",
            Icons.Filled.LibraryBooks,
            Icons.Outlined.LibraryBooks,
            StudentRoute.route
        ),
        BottomNavItem(
            "payments",
            Icons.Filled.CurrencyExchange,
            Icons.Outlined.CurrencyExchange,
            PaymentRoute.route
        ),
    )

    val justClassAuthNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                   null
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.Menu, contentDescription = null, tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = feintGrey
            ) {
                bottomNavItems.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        label = {
                            Text(text = bottomNavItem.title,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            selectedItemIndex = index
                            bottomNavItem.route },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    bottomNavItem.selectedIcon
                                } else bottomNavItem.unselectedIcon,
                                contentDescription =bottomNavItem.title,
                                tint = Color.Black
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(justClassAuthNavController,  HomeRoute.route, Modifier.padding(innerPadding)) {
            composable(HomeRoute.route) {
                HomeScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun viewMainNavigation() {
MainLayout()
}