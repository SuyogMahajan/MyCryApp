package com.example.mycryapp.data.models

import com.example.example.Coin
import com.google.gson.annotations.SerializedName

class MarketListData {
    @SerializedName("success"   ) var success   : Boolean? = null
    @SerializedName("crypto"     ) var crypto     : Map<String, Coin>?  = null
    @SerializedName("fiat"   ) var fiat   : Map<String, String?>? = null
}
