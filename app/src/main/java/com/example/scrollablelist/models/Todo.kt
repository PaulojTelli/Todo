package com.example.scrollablelist.models

import androidx.annotation.DrawableRes

data class Todo(
    @DrawableRes val picture: Int,
    val name: String,
    val status: String,
    val dueDate: String = ""  // Campo para a data da tarefa
)
