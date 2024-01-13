package com.example.example

import com.google.gson.annotations.SerializedName


data class MarketLiveData(

  @SerializedName("success"   ) var success   : Boolean? = null,
  @SerializedName("terms"     ) var terms     : String?  = null,
  @SerializedName("privacy"   ) var privacy   : String?  = null,
  @SerializedName("timestamp" ) var timestamp : Long?     = null,
  @SerializedName("target"    ) var target    : String?  = null,
  @SerializedName("rates"     ) var rates     : Map<String,String>?  = null

)