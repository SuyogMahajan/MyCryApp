package com.example.mycryapp.ui.activities

import CoinDataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Coin
import com.example.example.MarketLiveData
import com.example.mycryapp.R
import com.example.mycryapp.data.models.MarketListData
import com.example.mycryapp.databinding.ActivityMainBinding
import com.focus.cryptotracker.ui.adapters.CoinItemAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinDataViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var coinItemAdapter:CoinItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinDataViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel.getList().observe(this, Observer { list ->
//
//        })
//        viewModel.getLive().observe(this,{list ->
//
//        })

        coinItemAdapter = CoinItemAdapter(this)

        val onRvItemClick = object :CoinItemAdapter.onItemClickInterface{
            override fun onItemClick(coin: Coin) {

//                val i = Intent(requireContext(),CoinActivity::class.java);
//                i.putExtra(CLICKED_COIN,coin)
//                startActivity(i)
            }
        }

        coinItemAdapter.setOnItemClickInterface(onRvItemClick)
        binding.rvCoins.layoutManager = LinearLayoutManager(this)
        binding.rvCoins.adapter = coinItemAdapter

        refreshData()
    }

    private fun refreshData(){
        viewModel.getLive().observe(this,object:Observer<MarketLiveData>{
            override fun onChanged(value: MarketLiveData) {
                coinItemAdapter.updateMap(value.rates)
                binding.CoinRefreshLayout.isRefreshing = false
            }
        })
        viewModel.getList().observe(this,object:Observer<MarketListData>{
            override fun onChanged(value: MarketListData) {
                coinItemAdapter.updateList(value.crypto!!.values.toList())
                binding.CoinRefreshLayout.isRefreshing = false
            }
        })


    }
}