package ug.ledge.pangy.screens.dashboard.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ug.ledge.pangy.dataclasses.User
import ug.ledge.pangy.dataclasses.UsersRepository

class UserDetailViewModel(
    savedStateHandle: SavedStateHandle
//Use this to extract the state and return it to the Route Composable

):ViewModel() {
    var userState =  mutableStateOf<User?>(null)

    private  var usersRepository: UsersRepository = UsersRepository.getInstance()

    init{
        val userId  = savedStateHandle.get<String>("userId")?:""
        userState.value = usersRepository.getUserId(userId)
    }
}