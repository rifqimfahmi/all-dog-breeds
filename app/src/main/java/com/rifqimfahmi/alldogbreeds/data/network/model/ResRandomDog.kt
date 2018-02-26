package com.rifqimfahmi.alldogbreeds.data.network.model

import com.google.gson.annotations.SerializedName

data class ResRandomDog(@SerializedName("message")
                        val message: String = "",
                        @SerializedName("status")
                        val status: String = "")