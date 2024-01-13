package com.focus.cryptotracker.data.source.repository

import CoinRemoteDataSource
import android.content.Context
import com.example.example.MarketLiveData
import com.example.mycryapp.data.models.MarketListData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.example.mycryapp.utils.Result


class CoinRepository(val context: Context,
                     val ioDispatcher: CoroutineDispatcher
                     ) {

    val coinRemoteDataSource: CoinRemoteDataSource = CoinRemoteDataSource(ioDispatcher)

       // get List data
       suspend fun getList():Result<MarketListData> = withContext(ioDispatcher) {
           coinRemoteDataSource.getList()
       }

    // get live data
    suspend fun getLive(): Result<MarketLiveData> = withContext(ioDispatcher){

        val res = coinRemoteDataSource.getLive()
        res
    }

}
