package ug.ledge.pangy.screens.dashboard.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ug.ledge.pangy.dataclasses.Property
import ug.ledge.pangy.dataclasses.PropertyRepository
import ug.ledge.pangy.dataclasses.User
import ug.ledge.pangy.dataclasses.UsersRepository

class PropertyViewModel(
    private val propertyRepository: PropertyRepository = PropertyRepository()
    ):ViewModel() {
    init {

        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value=true
            delay(3000)
            val properties = getProperty()
            propertyState.value = properties
            isLoading.value=false
        }
    }
//    val propertyState:MutableState<Property?> = mutableStateOf(Property())
    val propertyState:MutableState<List<Property?>> = mutableStateOf(emptyList<Property>())
    val isLoading = mutableStateOf(false)

   suspend  fun getProperty():List<Property?>{
             return propertyRepository.getProperties()
            }


        }




