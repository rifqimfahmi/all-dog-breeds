package com.rifqimfahmi.alldogbreeds.data.network.model.giphy

import com.google.gson.annotations.SerializedName

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */

data class Meme (
        @SerializedName("slug")
        val slug: String,
        @SerializedName("url")
        val giphySource: String,
        @SerializedName("bitly_gif_url")
        val shortLink: String,
        @SerializedName("images")
        val images: MemeImage
)