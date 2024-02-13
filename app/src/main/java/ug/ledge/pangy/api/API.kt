package ug.ledge.pangy.api

import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ug.ledge.pangy.dataclasses.Property
import ug.ledge.pangy.dataclasses.PropertyImages
//import ug.ledge.pangy.dataclasses.Transactions
import ug.ledge.pangy.dataclasses.Transactions
import ug.ledge.pangy.dataclasses.User

interface API {
    @GET("property-listing")

    @Headers("Accept:application/json","Content-Type:application/json")
   suspend fun returnPropertyListing(): List<Property> //Remove the call property because we are
    //nolonger need to use enqueue and callbacks

    @GET("user-list")
    @Headers("Accept:application/json","Content-Type:application/json")
   suspend fun returnUsers(): List<User> //In ReCore this is a list

    @GET("user/{uid}")
    @Headers("Accept:application/json","Content-Type:application/json")
    suspend fun returnUser(@Path("uid") uid:String): User


   @GET("single-property-images/{property_id}")
   @Headers("Accept:application/json", "Content-Type:application/json")
   suspend fun returnPropertyImages(@Path("property_id") property:String):List<PropertyImages>

    @GET("transactions")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun returnAllTransactions():List<Transactions>

    @GET("user-transactions/{userId}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun returnUserTransactions(@Path("userId") userId: String):List<Transactions>
    //Filter locally
    @GET("search-transactions/{userId}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun searchMatch(@Query("term") transaction:String, @Path("userId") userId:String):List<Transactions>

    @GET("post-transaction/")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun postTransaction(@Body params: Transactions): List<Transactions>


}