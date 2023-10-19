package com.example.mobifilatask.api.myinterface


import com.example.neosaotask.api.api.model.UniverSityResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall {
    @GET("entries")
    fun getResponse(): Call<UniverSityResponse>
}