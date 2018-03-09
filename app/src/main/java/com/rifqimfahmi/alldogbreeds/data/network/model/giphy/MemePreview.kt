package com.rifqimfahmi.alldogbreeds.data.network.model.giphy

import com.google.gson.annotations.SerializedName

/*
 * Created by Rifqi Mulya Fahmi on 07/03/18.
 */

data class MemePreview (
        @SerializedName("url")
        val url: String?
)