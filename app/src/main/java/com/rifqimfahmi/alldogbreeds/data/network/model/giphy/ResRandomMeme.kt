package com.rifqimfahmi.alldogbreeds.data.network.model.giphy

import com.google.gson.annotations.SerializedName

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

data class ResRandomMeme (
        @SerializedName("data")
        val data: ArrayList<Meme>?
)