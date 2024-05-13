package com.example.scrollablelist.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.scrollablelist.models.Todo
import com.example.scrollablelist.viewmodel.TodoListViewModel
import java.util.*

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.annotation.DrawableRes


@Composable
fun TodoList(
    viewModel: TodoListViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val uiState by viewModel.todoListUiState.collectAsState()
    LazyColumn {
        items(uiState.todoList) { todo ->
            TodoCard(
                todo = todo,
                onDelete = viewModel::deleteTodo,
                onEditTodo = {
                    viewModel.updateTodo(
                        todo = todo,
                        navController = navController
                    )
                })
        }
    }
}



// Sua definição da classe Todo
data class Todo(
    @DrawableRes val picture: Int,
    val name: String,
    val status: String,
    val dueDate: String = ""  // Campo para a data da tarefa
)



@Composable
fun TodoCard(
    todo: Todo,
    onDelete: (Todo) -> Unit,
    onEditTodo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
            .clickable {
                onEditTodo()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(50.dp),
                painter = painterResource(id = todo.picture),
                contentDescription = null,
            )
            Column(modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)) {
                Text(text = todo.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)

                Text(
                    modifier = Modifier.width(260.dp),
                    text = todo.status,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black,
                    maxLines = 2
                )

                Text(
                    text = todo.dueDate,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Image(
                painter = painterResource(id = com.example.scrollablelist.R.drawable.baseline_face_5_24,),
                contentDescription = "delete",
                modifier = Modifier.clickable {
                    onDelete(todo)
                }
            )
        }
    }
}

// Função de Pré-visualização para TodoCard
@Preview(showBackground = true)
@Composable
fun PreviewTodoCard() {
    // Crie uma instância de Todo para passar para TodoCard
    val sampleTodo = Todo(
        picture = com.example.scrollablelist.R.drawable.baseline_face_5_24,
        name = "Sample Task",
        status = "In Progress",
        dueDate = "2024-05-15"
    )

    // Chame TodoCard com dados de amostra
    TodoCard(
        todo = sampleTodo,
        onDelete = {},
        onEditTodo = {}
    )
}