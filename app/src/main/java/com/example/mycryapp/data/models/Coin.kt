package com.example.example

import com.google.gson.annotations.SerializedName


data class Coin (

    @SerializedName("symbol"     ) var symbol    : String? = null,
    @SerializedName("name"       ) var name      : String? = null,
    @SerializedName("name_full"  ) var nameFull  : String? = null,
    @SerializedName("max_supply" ) var maxSupply : String?    = null,
    @SerializedName("icon_url"   ) var iconUrl   : String? = null

)