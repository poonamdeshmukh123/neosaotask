package com.example.mobifilatask.api.apiinterface

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient
{
    companion object
    {
        val baseurl:String="https://api.publicapis.org/"
        fun getRetrofitInstance():Retrofit
        {
return Retrofit.Builder()
    .baseUrl(baseurl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
        }
    }
}