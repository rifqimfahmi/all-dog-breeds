package com.rifqimfahmi.alldogbreeds.data.network.model

import com.google.gson.annotations.SerializedName

data class ResBreedImages(@SerializedName("message")
                       val images: ArrayList<String>?,
                          @SerializedName("status")
                       val status: String = "")