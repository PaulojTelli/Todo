package com.example.scrollablelist.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.scrollablelist.R
import com.example.scrollablelist.data.createTodosList
import com.example.scrollablelist.models.Todo
import com.example.scrollablelist.ui.views.AppScreens
import com.example.scrollablelist.ui.views.InsertTodoUiState
import com.example.scrollablelist.ui.views.MainScreenUiState
import com.example.scrollablelist.ui.views.TodoListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoListViewModel : ViewModel() {
    private val _todoListUiState: MutableStateFlow<TodoListUiState> =
        MutableStateFlow(TodoListUiState(createTodosList()))

    val todoListUiState: StateFlow<TodoListUiState> =
        _todoListUiState.asStateFlow()

    private val _insertTodoUiState: MutableStateFlow<InsertTodoUiState> =
        MutableStateFlow(InsertTodoUiState())

    val insertTodoUiState: StateFlow<InsertTodoUiState> =
        _insertTodoUiState.asStateFlow()

    private val _mainScreenUiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(
            MainScreenUiState(
                "Todo List",
                R.drawable.baseline_add_24,
                "Insert Todo"
            )
        )

    val mainScreenUiState: StateFlow<MainScreenUiState> =
        _mainScreenUiState.asStateFlow()

    private var editTodo: Boolean = false
    private var todoToEdit: Todo = Todo(
        R.drawable.baseline_face_24,
        "",
        "",
    )

    fun fabAction(navController: NavController) {
        if (_mainScreenUiState.value.screenName == "Todo List") {
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenName = "Insert Todo",
                    icon = R.drawable.baseline_check_24,
                    iconContentDescription = "Confirm"
                )
            }
            navController.navigate(AppScreens.InsertTodo.name)
        } else {
            if (editTodo) {
                val allTodos = _todoListUiState.value.todoList.toMutableList()
                val pos = allTodos.indexOf(todoToEdit)
                allTodos.remove(todoToEdit)
                allTodos.add(
                    pos, todoToEdit.copy(
                        picture = _insertTodoUiState.value.picture,
                        name = _insertTodoUiState.value.name,
                        status = _insertTodoUiState.value.status,
                        dueDate = _insertTodoUiState.value.dueDate // Atualizar a data aqui
                    )
                )
                _todoListUiState.update { currentState ->
                    currentState.copy(
                        todoList = allTodos.toList()
                    )
                }
                editTodo = false
                todoToEdit = Todo(R.drawable.baseline_face_24, "", "")
            } else {
                _todoListUiState.update { currentState ->
                    currentState.copy(
                        todoList = currentState.todoList + Todo(
                            _insertTodoUiState.value.picture,
                            _insertTodoUiState.value.name,
                            _insertTodoUiState.value.status,
                            _insertTodoUiState.value.dueDate // Atualizar a data aqui
                        )
                    )
                }
            }
            _insertTodoUiState.update {
                InsertTodoUiState()
            }
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenName = "Todo List",
                    icon = R.drawable.baseline_add_24,
                    iconContentDescription = "Insert Todo"
                )
            }
            navController.navigate(AppScreens.TodoList.name) {
                popUpTo(AppScreens.TodoList.name) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateBack(navController: NavController) {
        editTodo = false
        todoToEdit = Todo(R.drawable.baseline_face_24, "", "")
        _insertTodoUiState.update { InsertTodoUiState() }
        _mainScreenUiState.update {
            MainScreenUiState(
                screenName = "Todo List",
                icon = R.drawable.baseline_add_24,
                iconContentDescription = "Insert Todo"
            )
        }
        navController.popBackStack()
    }

    fun updateTodo(todo: Todo, navController: NavController) {
        editTodo = true
        todoToEdit = todo
        _insertTodoUiState.update { currentState->
            currentState.copy(
                picture = todo.picture,
                name = todo.name,
                status = todo.status,
                dueDate = todo.dueDate // Atualizar a data aqui
            )
        }
        _mainScreenUiState.update {
            MainScreenUiState(
                screenName = "Edit Todo",
                icon = R.drawable.baseline_check_24,
                iconContentDescription = "Confirm"
            )
        }
        navController.navigate(AppScreens.InsertTodo.name)
    }

    fun updatePicture(@DrawableRes newPicture: Int) {
        _insertTodoUiState.value = _insertTodoUiState.value.copy(
            picture = newPicture
        )
    }

    fun updateName(newName: String) {
        _insertTodoUiState.value = _insertTodoUiState.value.copy(
            name = newName
        )
    }

    fun updateStatus(newStatus: String) {
        _insertTodoUiState.value = _insertTodoUiState.value.copy(
            status = newStatus
        )
    }

    fun updateDueDate(newDueDate: String) {
        _insertTodoUiState.value = _insertTodoUiState.value.copy(
            dueDate = newDueDate
        )
    }

    fun deleteTodo(todo: Todo) {
        val todos = _todoListUiState.value.todoList.toMutableList()
        todos.remove(todo)
        _todoListUiState.value = _todoListUiState.value.copy(
            todoList = todos.toList()
        )
    }
}
