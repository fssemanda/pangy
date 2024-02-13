package ug.ledge.pangy.dataclasses

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ug.ledge.pangy.api.Webservice

class PropertyRepository (private val webservice: Webservice = Webservice()){


    private  var cachedPropertyList = listOf<Property>()
    suspend fun getProperties():List<Property> {
        val response = webservice.getProperty()
        cachedPropertyList = response.toList()
        return  response
    }

    fun getPropertyId(id: String):Property?{
        return cachedPropertyList.firstOrNull{
            it.propertyName == id
        }
    }


    suspend fun getPropertyImages(property_id:String):List<PropertyImages>{
        val response = webservice.returnPropertyImages(property_id)
        return response
    }
}