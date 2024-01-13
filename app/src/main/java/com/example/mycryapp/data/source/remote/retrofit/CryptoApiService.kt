package com.focus.cryptotracker.data.source.remote.retrofit

import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiEndPoint.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApiService {

    @GET("list")
    suspend fun getListData(@Query("access_key")curr:String = API_KEY): Response<MarketListData>

    @GET("live")
    suspend fun getLiveData(@Query("access_key")curr:String = API_KEY): Response<MarketLiveData>

}
