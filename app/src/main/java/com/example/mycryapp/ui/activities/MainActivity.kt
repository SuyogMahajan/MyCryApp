package com.example.mycryapp.ui.activities

import CoinDataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Coin
import com.example.example.MarketLiveData

import com.example.mycryapp.utils.Result
import com.example.mycryapp.data.models.MarketListData
import com.example.mycryapp.databinding.ActivityMainBinding
import com.focus.cryptotracker.ui.adapters.CoinItemAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinDataViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var coinItemAdapter:CoinItemAdapter
    private var loading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinDataViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coinItemAdapter = CoinItemAdapter(this)

        val onRvItemClick = object :CoinItemAdapter.onItemClickInterface{
            override fun onItemClick(coin: Coin) {

            }
        }

        coinItemAdapter.setOnItemClickInterface(onRvItemClick)
        binding.rvCoins.layoutManager = LinearLayoutManager(this)
        binding.rvCoins.adapter = coinItemAdapter

        refreshData()

        binding.CoinRefreshLayout.setOnRefreshListener {
            refreshData()
        }

    }

    private fun refreshData(){
        viewModel.getLive().observe(this,object:Observer<Result<MarketLiveData>>{

            override fun onChanged(value: Result<MarketLiveData>) {

                when(value) {
                    is Result.Success -> {
                        loading = false
                        coinItemAdapter.updateMap(value.data!!.rates)
                    }
                    is Result.Error -> {
                        loading = false
                        Log.d("Message!!",value.exception.message!!)
                    }
                    else -> {
                        loading = true
                    }
                }

                binding.CoinRefreshLayout.isRefreshing = false
            }
        })

        viewModel.getList().observe(this,object:Observer<Result<MarketListData>>{
            override fun onChanged(value: Result<MarketListData>) {
                when(value) {
                    is Result.Success -> {
                        loading = false
                        coinItemAdapter.updateList(value.data!!.crypto!!.values.toList())
                    }
                    is Result.Error -> {
                        loading = false
                        Log.d("Message!!",value.exception.message!!)
                    }
                    else -> {
                        loading = true
                    }
                }

                binding.CoinRefreshLayout.isRefreshing = false
            }
        })

    }
}