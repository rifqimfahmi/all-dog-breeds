package com.rifqimfahmi.alldogbreeds.data.network.model

import com.google.gson.annotations.SerializedName

data class Breeds(@SerializedName("message")
                  val message: List<String>?,
                  @SerializedName("status")
                  val status: String = "")