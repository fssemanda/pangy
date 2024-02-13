package ug.ledge.pangy.api

import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Response
import ug.ledge.pangy.dataclasses.Property
import ug.ledge.pangy.dataclasses.PropertyImages
import ug.ledge.pangy.dataclasses.Transactions
import ug.ledge.pangy.dataclasses.User

private lateinit var propertiesAPI:API

class Webservice {

    init {

        val retrofitBuilderClass = RetrofitBuilderClass.getRetroInstance().create(API::class.java)
        propertiesAPI = retrofitBuilderClass
    }

    suspend fun getProperty(): List<Property> {
        return propertiesAPI.returnPropertyListing()

    }
   suspend fun getUsers(): List<User>{
        return propertiesAPI.returnUsers()
    }
    suspend fun returnUser(userId:String): User{
        return propertiesAPI.returnUser(userId)
    }

    suspend fun returnPropertyImages(property_id:String):List<PropertyImages>{
        return propertiesAPI.returnPropertyImages(property_id)
    }
    suspend fun getUserTransactions(userId:String):List<Transactions>{
        return propertiesAPI.returnUserTransactions(userId)
    }
}