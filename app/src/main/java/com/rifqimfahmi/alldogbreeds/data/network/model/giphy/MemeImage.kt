package com.rifqimfahmi.alldogbreeds.data.network.model.giphy

import com.google.gson.annotations.SerializedName

/*
 * Created by Rifqi Mulya Fahmi on 26/02/18.
 */
 
data class MemeImage (
        @SerializedName("preview_gif")
        val preview: MemePreview,
        @SerializedName("downsized_medium")
        val downsized_medium: MemeDownSizedMedium,
        @SerializedName("original")
        val original: MemeOriginal
)