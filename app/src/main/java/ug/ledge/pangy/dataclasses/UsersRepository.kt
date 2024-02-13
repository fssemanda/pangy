package ug.ledge.pangy.dataclasses

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ug.ledge.pangy.api.Webservice

class UsersRepository (private val webservice: Webservice = Webservice()) {

    private var cachedUsers = listOf<User>()
    private var singleUser = mutableStateOf<User?>(null)
//    (User(
//        null,null,null,
//        null,null,null,null,null,null,null,
//        null,null,null
//    ))
   suspend fun getUsers():List<User> {


        val response = webservice.getUsers()
        cachedUsers = response.toList()
        return response

   }
    //Method helps return the value of the user Id from the Data class object back into the Users List view Model
    fun getUserId(username:String): User?{
        /// Function returns details of our first user that matches a given ID when called from the UserDetail VM

        return cachedUsers.firstOrNull{
            it.username == username
        }
    }

    companion object{
        @Volatile
        private var instance: UsersRepository? = null
        //Wherever the Users List Repository object is instantiated, let's use this get instance so that there are no overwrites
        fun getInstance() = instance?: synchronized(this){
            instance ?: UsersRepository().also {
                instance = it
            }
        }
    }
    suspend fun returnUser(userId:String):MutableState<User?>{
            val user = webservice.returnUser(userId)
            singleUser = mutableStateOf(user)
       return singleUser
    }
}
