package com.example.mycryapp.ui.activities

import CoinDataViewModel
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Coin
import com.example.example.MarketLiveData

import com.example.mycryapp.utils.Result
import com.example.mycryapp.data.models.MarketListData
import com.example.mycryapp.databinding.ActivityMainBinding
import com.focus.cryptotracker.ui.adapters.CoinItemAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinDataViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var coinItemAdapter:CoinItemAdapter
    private lateinit var mainHandler: Handler
    private var loading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainHandler = Handler(Looper.getMainLooper())
        viewModel = ViewModelProvider(this).get(CoinDataViewModel::class.java)
        coinItemAdapter = CoinItemAdapter(this)

        val onRvItemClick = object :CoinItemAdapter.onItemClickInterface{
            override fun onItemClick(coin: Coin) {
                // this will work if any item is clicked
            }
        }

        coinItemAdapter.setOnItemClickInterface(onRvItemClick)
        binding.rvCoins.layoutManager = LinearLayoutManager(this)
        binding.rvCoins.adapter = coinItemAdapter

        refreshData()

        binding.CoinRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        binding.floatingButton.setOnClickListener {
            binding.CoinRefreshLayout.isRefreshing = true;
            refreshData()
        }

        mainHandler.post(object :Runnable {
            override fun run() {
                refreshData()
                mainHandler.postDelayed(this,180000)
            }
        })

    }

    private fun refreshData(){
        binding.onlineStatus.visibility = View.GONE
        binding.floatingButton.visibility = View.GONE

        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val curr = formatter.format(Date())
        binding.lastRefreshed.text = curr.toString()
        viewModel.getLive().observe(this,object:Observer<Result<MarketLiveData>>{

            override fun onChanged(value: Result<MarketLiveData>) {

                when(value) {
                    is Result.Success -> {
                        loading = false
                        coinItemAdapter.updateMap(value.data!!.rates)
                    }
                    is Result.Error -> {
                        loading = false
                        binding.onlineStatus.visibility = View.VISIBLE
                        binding.floatingButton.visibility = View.VISIBLE
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
                        binding.onlineStatus.visibility = View.VISIBLE
                        binding.floatingButton.visibility = View.VISIBLE
                            Log.d("Message!!", value.exception.message!!)
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