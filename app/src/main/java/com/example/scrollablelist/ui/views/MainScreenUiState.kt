package com.example.scrollablelist.ui.views

import androidx.annotation.DrawableRes

data class MainScreenUiState(
    val screenName: String,
    @DrawableRes val icon: Int,
    val iconContentDescription: String,
)
