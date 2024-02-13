package ug.ledge.pangy.screens.dashboard.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ug.ledge.pangy.dataclasses.User
import ug.ledge.pangy.dataclasses.UsersRepository

class ProfileViewModel(
    private  val usersRepository: UsersRepository = UsersRepository.getInstance()
):ViewModel() {
    var isLoading = mutableStateOf(false)
    fun returnUserDetails(userName:String){
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val user = returnUser(userName)
            userState = user
            isLoading.value = false
        }
    }
    //Instantiate the state from the viewModel not the screen composable that requries this data
//    val UsersState: MutableState<User?> = mutableStateOf<User?>(User(
//        null,null,null,
//        null,null,null,null,null,null,null,
//        null,null,null
//    ))

    var userState = mutableStateOf<User?>(null)
//        private set

    private suspend fun returnUser(userName:String): MutableState<User?>{
        return usersRepository.returnUser(userName)
    }
}