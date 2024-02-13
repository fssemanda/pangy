package ug.ledge.pangy.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderClass {
    companion object{
//        const val BASE_URL ="http://127.0.0.1:8000/api-endpoint/"
        const val BASE_URL ="https://pangisha.ledge.ug/api-endpoint/"

        fun getRetroInstance(): Retrofit {

            val logging =  HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}