package com.example.scrollablelist.ui.views

import android.app.DatePickerDialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scrollablelist.R
import com.example.scrollablelist.viewmodel.TodoListViewModel

import java.text.SimpleDateFormat
import java.util.*






@Composable
fun InsertTodo(
    viewModel: TodoListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.insertTodoUiState.collectAsState()
    BackHandler {
        viewModel.navigateBack(navController = navController)
    }
    InsertForm(
        picture = uiState.picture,
        name = uiState.name,
        status = uiState.status,
        dueDate = uiState.dueDate,
        onUpdatePicture = viewModel::updatePicture,
        onUpdateName = viewModel::updateName,
        onUpdateStatus = viewModel::updateStatus,
        onUpdateDueDate = viewModel::updateDueDate,
        modifier = Modifier,
    )
}

@Composable
fun InsertForm(
    @DrawableRes picture: Int,
    name: String,
    status: String,
    dueDate: String,
    onUpdatePicture: (Int) -> Unit,
    onUpdateStatus: (String) -> Unit,
    onUpdateName: (String) -> Unit,
    onUpdateDueDate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        R.drawable.baseline_face_24,
        R.drawable.baseline_face_2_24,
        R.drawable.baseline_face_3_24,
        R.drawable.baseline_face_4_24,
        R.drawable.baseline_face_5_24,
        R.drawable.baseline_face_6_24
    )
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth()
        ) {
            items(imageList) { image ->
                Box(modifier = modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .background(if (image == picture) Color.LightGray else Color.Transparent)
                ){
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clickable {
                                onUpdatePicture(image)
                            }
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = onUpdateName,
            label = { Text("Nome") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = status,
            onValueChange = onUpdateStatus,
            label = { Text("Status") },
            singleLine = false,
            minLines = 1,
            maxLines = 3,
            modifier = modifier.fillMaxWidth()
        )

        // Campo de seleção de data
        val context = LocalContext.current
        val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
        val calendar = remember { Calendar.getInstance() }
        if (dueDate.isNotEmpty()) {
            try {
                calendar.time = dateFormat.parse(dueDate) ?: Calendar.getInstance().time
            } catch (e: Exception) { }
        }
        val dateStr = remember { mutableStateOf(TextFieldValue(dueDate)) }

        OutlinedTextField(
            value = dateStr.value,
            onValueChange = { dateStr.value = it },
            label = { Text("Data de Vencimento") },
            singleLine = true,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Selecionar Data",
                    modifier = Modifier.clickable {
                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                calendar.set(year, month, dayOfMonth)
                                val newDateStr = dateFormat.format(calendar.time)
                                dateStr.value = TextFieldValue(newDateStr)
                                onUpdateDueDate(newDateStr)
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
