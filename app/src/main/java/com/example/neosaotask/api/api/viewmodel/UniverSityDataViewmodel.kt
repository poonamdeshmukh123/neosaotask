package com.example.neosaotask.api.api.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobifilatask.api.apiinterface.RetrofitClient
import com.example.mobifilatask.api.myinterface.ApiCall
import com.example.neosaotask.api.api.model.UniverSityResponse
import retrofit2.Call
import retrofit2.Response

class UniverSityDataViewmodel : ViewModel()
{
    lateinit var api: ApiCall
    var data = MutableLiveData<UniverSityResponse>()

    init {
        api = RetrofitClient.getRetrofitInstance().create(ApiCall::class.java)
    }

    fun getUniversitydataResponse() {
        try {


            val call = api.getResponse()
            call.enqueue(object : retrofit2.Callback<UniverSityResponse> {
                override fun onResponse(call: Call<UniverSityResponse>, response: Response<UniverSityResponse>) {
                    data.value = response.body()

                }

                override fun onFailure(call: Call<UniverSityResponse>, t: Throwable) {

                }

            })
        } catch (e: Exception) {
            Log.d("exception", e.toString())
        }
    }
    fun getUpdatedResponse():MutableLiveData<UniverSityResponse>
    {
        return data
    }
}