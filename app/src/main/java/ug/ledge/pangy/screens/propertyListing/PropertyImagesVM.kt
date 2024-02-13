package ug.ledge.pangy.screens.propertyListing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ug.ledge.pangy.dataclasses.PropertyImages
import ug.ledge.pangy.dataclasses.PropertyRepository

class PropertyImagesVM(
    private val propertyImagesRepository:PropertyRepository= PropertyRepository()
):ViewModel() {
    val isLoading = mutableStateOf(true)
    init {
        viewModelScope.launch(Dispatchers.IO) {
//            isLoading.value=true
//            delay(3000)
            val properties = returnSinglePropertyImages()
            rememberedPropertyState.value = properties
            isLoading.value=false

        }
    }
    val rememberedPropertyState =  mutableStateOf(emptyList<PropertyImages>())

    suspend fun returnSinglePropertyImages(): List<PropertyImages> {
        return propertyImagesRepository.getPropertyImages("2")
    }

}