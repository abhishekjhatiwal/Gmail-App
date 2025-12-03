package com.example.gmailapp.core_ui.uimodel

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface DrawerData {
    data class Title(val title: String) : DrawerData
    data class Category(val title: String) : DrawerData
    data class Item(val title: String, val icon: ImageVector) : DrawerData
    data object Divider : DrawerData
}

data class DropDownMenuItem(
    val title: String,
    val onClick: () -> Unit
)