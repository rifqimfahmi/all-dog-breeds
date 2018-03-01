package com.rifqimfahmi.alldogbreeds.data.network.model.giphy

import com.google.gson.annotations.SerializedName

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */
 
data class MemeOriginal (
        @SerializedName("url")
        val url: String?,
        @SerializedName("webp")
        val webp: String?
)