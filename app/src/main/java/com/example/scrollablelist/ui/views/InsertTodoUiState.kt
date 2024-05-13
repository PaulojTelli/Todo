package com.example.scrollablelist.ui.views

import androidx.annotation.DrawableRes
import com.example.scrollablelist.R

data class InsertTodoUiState(
    @DrawableRes val picture: Int = R.drawable.baseline_face_24,
    val name: String = "",
    val status: String = "",
    val dueDate: String = ""  // Adicione este campo para a data
)
