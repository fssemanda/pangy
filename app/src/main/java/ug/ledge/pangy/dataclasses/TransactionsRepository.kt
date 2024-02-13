package ug.ledge.pangy.dataclasses

import ug.ledge.pangy.api.Webservice

class TransactionsRepository(private var webservice: Webservice=Webservice()) {

    suspend fun getUserTransactions(userId:String):List<Transactions>{
        return webservice.getUserTransactions(userId)
    }
}