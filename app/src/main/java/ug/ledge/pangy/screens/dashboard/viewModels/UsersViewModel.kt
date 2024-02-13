package ug.ledge.pangy.screens.dashboard.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ug.ledge.pangy.dataclasses.User
import ug.ledge.pangy.dataclasses.UsersRepository

class UsersViewModel(
    private  val usersRepository: UsersRepository = UsersRepository.getInstance()
): ViewModel(){



    //instantiate job to create our own scope
    private val usersJob = Job()
    //launch the coroutine when th viewmodel is initiated so that we get data from the server


    init {
        val scope = CoroutineScope(usersJob + Dispatchers.IO)// use this if we need to define our own scope

        viewModelScope.launch(Dispatchers.IO) {
            val users = getUsers()
            UsersState.value = users
        }
    }
    //Instantiate the state from the viewModel not the screen composable that requries this data
    val UsersState: MutableState<List<User?>> = mutableStateOf(emptyList<User>())

    //Only need this to clear the Job if we are using our own scope
//    override fun onCleared() {
//        super.onCleared()
//        usersJob.cancel()
//    }

    private suspend fun getUsers(): List<User>{
        return usersRepository.getUsers ()
        }
    }
