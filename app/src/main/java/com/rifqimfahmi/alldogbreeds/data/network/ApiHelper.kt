package com.rifqimfahmi.alldogbreeds.data.network

import com.rifqimfahmi.alldogbreeds.data.network.model.Breeds
import rx.Observable

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

interface ApiHelper {
    fun getDogApi(): DogApi
    fun getGiphyApi(): GiphyApi
}