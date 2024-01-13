package com.focus.cryptotracker.data.source.repository

import CoinRemoteDataSource
import android.content.Context
import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CoinRepository(val context: Context,
                     val ioDispatcher: CoroutineDispatcher
                     ) {

    val coinRemoteDataSource: CoinRemoteDataSource = CoinRemoteDataSource(ioDispatcher)

       // get List data
       suspend fun getList():MarketListData = withContext(ioDispatcher){

           val res = coinRemoteDataSource.getList()
           res
       }

    // get live data
    suspend fun getLive(): MarketLiveData = withContext(ioDispatcher){

        val res = coinRemoteDataSource.getLive()
        res
    }

}
