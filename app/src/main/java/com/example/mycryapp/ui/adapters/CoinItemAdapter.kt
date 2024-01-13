package com.focus.cryptotracker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Coin
import com.example.mycryapp.databinding.ListItemCoinDetailsBinding

class CoinItemAdapter(val context: Context):RecyclerView.Adapter<CoinItemAdapter.getViewHolder>() {

    interface onItemClickInterface{
        fun onItemClick(coin: Coin)
    }


   private lateinit var onItemClick:onItemClickInterface

    fun setOnItemClickInterface(click:onItemClickInterface){
        onItemClick = click
    }

   private var list:ArrayList<Coin> =  ArrayList<Coin>()
   private var prices:Map<String,String>? = null

    inner class getViewHolder(val binding: ListItemCoinDetailsBinding, val onItemClicked:onItemClickInterface):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.nameTv.text = list[position].nameFull
            binding.prizeTv.text = "$" + prices!![list[position].symbol]
            binding.totalSupplyTv.text = "$" +list[position].maxSupply
            Glide.with(context).load(list[position].iconUrl).into(binding.iconIV)

            binding.root.setOnClickListener {
                onItemClick.onItemClick(list[position])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        return getViewHolder(ListItemCoinDetailsBinding.inflate(LayoutInflater.from(context),parent,false),onItemClick)
    }

    override fun onBindViewHolder(holder: getViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Coin>){

        if(updatedList.isNullOrEmpty()){
            list.clear()
        }else{
            list = updatedList as ArrayList<Coin>
        }

        notifyDataSetChanged()
    }

    fun updateMap(updatedMap: Map<String,String>?){

        if(updatedMap.isNullOrEmpty()){
            prices = null
        }else{
            prices = updatedMap
        }

        notifyDataSetChanged()
    }
}