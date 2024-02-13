package ug.ledge.pangy.Transactions

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ug.ledge.pangy.dataclasses.Transactions
import ug.ledge.pangy.dataclasses.TransactionsRepository
import ug.ledge.pangy.dataclasses.User
import ug.ledge.pangy.dataclasses.UsersRepository

class TransactionsViewModel(
private val savedStateHandle: SavedStateHandle
,

):ViewModel() {
    var userState = mutableStateOf<User?>(null)
    //When we instantiate a usersRepository object, ots a new instantiation so it has no idea of values stored by
    //the one in UsersViewModel. So We need to get an instance of the one already instantiated using the
    //Companion object in the UsersRepository
    private  val usersRepository: UsersRepository = UsersRepository.getInstance()
    private var transactionsRepository: TransactionsRepository = TransactionsRepository()
    var myState: String?
        get() = savedStateHandle["userId"]
        set(value) {
            savedStateHandle["userId"] = value
        }
//    init
//    {
//        val userId = savedStateHandle.get<String>("userId")?:""
//        Log.d("Francis2","$userId")
//
//        viewModelScope.launch(Dispatchers.IO) {
////            userState.value = usersRepository.getUserId(userId)
////            Log.d("Francis3","${userState.value}")
//            val userTransactions = getUserTransactions(userId)
//            userTransactionState.value = userTransactions
//        }
//    }

    fun returnUserTransactions(userId:String){

                viewModelScope.launch(Dispatchers.IO) {
//            userState.value = usersRepository.getUserId(userId)
//            Log.d("Francis3","${userState.value}")
            val userTransactions = getUserTransactions(userId)
            userTransactionState.value = userTransactions

        }
    }

    val userTransactionState:MutableState<List<Transactions>> = mutableStateOf(emptyList<Transactions>())

    suspend fun getUserTransactions(userId:String):List<Transactions>{
        return transactionsRepository.getUserTransactions(userId)
    }
}