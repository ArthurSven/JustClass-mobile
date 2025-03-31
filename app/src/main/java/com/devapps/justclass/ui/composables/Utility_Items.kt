package com.devapps.justclass.ui.composables

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

val languageLevelList = listOf("A1", "A2", "B1", "B2", "C1", "C2")