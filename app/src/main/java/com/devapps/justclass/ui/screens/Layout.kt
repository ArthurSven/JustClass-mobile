package com.devapps.justclass.ui.screens

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.bitmapConfig
import com.devapps.justclass.R
import com.devapps.justclass.Utils.AddStudentRoute
import com.devapps.justclass.Utils.ClassroomRoute
import com.devapps.justclass.Utils.HomeRoute
import com.devapps.justclass.Utils.HomeworkRoute
import com.devapps.justclass.Utils.PaymentRoute
import com.devapps.justclass.Utils.SignOutRoute
import com.devapps.justclass.Utils.StudentRoute
import com.devapps.justclass.Utils.WelcomeRoute
import com.devapps.justclass.data.model.UserData
import com.devapps.justclass.ui.composables.BottomNavItem
import com.devapps.justclass.ui.theme.feintGrey
import com.devapps.justclass.ui.viewmodels.StudentViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
justClassMainNavController: NavController,
userData: UserData?,
onSignOut: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val showMenu = remember { mutableStateOf(false) }
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val studentViewModel: StudentViewModel = koinViewModel { parametersOf(userData) }

    BackHandler {
        justClassMainNavController.popBackStack(WelcomeRoute.route, false)
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
            HomeworkRoute.route
        ),
        BottomNavItem(
            "payment",
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
                actions = {
                    if(userData?.userProfileUrl != null) {
                        val req = ImageRequest.Builder(context)
                            .data(userData.userProfileUrl)
                            .bitmapConfig(Bitmap.Config.RGB_565)
                            .allowHardware(false)
                            .build()
                        AsyncImage(
                            model = req,
                            contentDescription = "${userData.username}'s profile picture",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .clickable {
                                    showMenu.value = !showMenu.value
                                },
                            contentScale = ContentScale.Crop
                        )
                        DropdownMenu(
                            expanded = showMenu.value,
                            onDismissRequest = {
                                showMenu.value = false
                            },
                            modifier = Modifier
                                .background(color = Color.White)
                                .width(150.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Logout,
                                    contentDescription = "logout",
                                    tint = Color.DarkGray
                                )
                                Spacer(
                                    modifier = Modifier
                                        .width(5.dp)
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "Logout",
                                            color = Color.Black)
                                    },
                                    onClick = {
                                        justClassMainNavController.navigate(SignOutRoute.route)
                                        onSignOut()
                                    },
                                    modifier = Modifier
                                        .background(color = Color.White)
                                )
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(R.drawable.no_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .clickable {
                                    showMenu.value = !showMenu.value
                                },
                            contentScale = ContentScale.Crop
                        )
                        DropdownMenu(
                            expanded = showMenu.value,
                            onDismissRequest = {
                                showMenu.value = false
                            },
                            modifier = Modifier
                                .background(color = Color.White)
                                .width(80.dp)
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                            ) {
                                Icon(imageVector = Icons.Outlined.Logout, contentDescription = "logout")
                                Spacer(
                                    modifier = Modifier
                                        .width(8.dp)
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "Logout",
                                            color = Color.Black)
                                    },
                                    onClick = {
                                        justClassMainNavController.navigate(SignOutRoute.route)
                                        onSignOut()
                                    },
                                    modifier = Modifier
                                        .background(color = Color.White)
                                )
                            }
                        }
                    }
                }
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
                            justClassAuthNavController.navigate(bottomNavItem.route) {
                                popUpTo(justClassAuthNavController.graph.startDestinationId)
                            }
                                  },
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
        NavHost(justClassAuthNavController, startDestination =  HomeRoute.route, Modifier.padding(innerPadding)) {
            composable(HomeRoute.route) {
                HomeScreen()
            }
            composable(ClassroomRoute.route) {
                ClassListScreen()
            }
            composable(StudentRoute.route) {
                StudentListScreen(
                    justClassAuthNavController,
                    studentViewModel
                )
            }
            composable(AddStudentRoute.route) {
                CreateStudentScreen(
                     userData,
                    studentViewModel
                )
            }
            composable(HomeworkRoute.route) {
                HomeworkListScreen()
            }
            composable(PaymentRoute.route) {
                PaymentListScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun viewMainNavigation() {

}