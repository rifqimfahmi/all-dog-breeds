package com.rifqimfahmi.alldogbreeds.data.db.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import java.util.*

/*
 * Created by Rifqi Mulya Fahmi on 07/03/18.
 */

open class DogMeme (
        var slug: String? = null,
        var giphy_source: String? = null,
        var giphy_shortlink: String? = null,
        var original_gif: String? = null,
        var downsized_medium_gif: String? = null,
        var preview_gif: String? = null
) : RealmObject(), Parcelable {

    constructor(parcel: Parcel) : this (
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(slug)
        parcel.writeString(giphy_source)
        parcel.writeString(giphy_shortlink)
        parcel.writeString(original_gif)
        parcel.writeString(downsized_medium_gif)
        parcel.writeString(preview_gif)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DogMeme> {
        override fun createFromParcel(parcel: Parcel): DogMeme {
            return DogMeme(parcel)
        }

        override fun newArray(size: Int): Array<DogMeme?> {
            return arrayOfNulls(size)
        }
    }

}