package com.rifqimfahmi.alldogbreeds.data.network

import com.rifqimfahmi.alldogbreeds.data.network.model.giphy.ResRandomMeme
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */
 
interface GiphyApi {
    companion object {
        val ENDPOINT: String = "http://api.giphy.com/v1/gifs/"
    }

    @GET("search?q=dog&limit=1")
    fun getRandomDogMeme(@Query("offset") offset: Int, @Query("api_key") api_key: String): Observable<ResRandomMeme>
}