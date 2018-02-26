package com.rifqimfahmi.alldogbreeds.data.network

import com.rifqimfahmi.alldogbreeds.data.network.model.ResBreedImages
import com.rifqimfahmi.alldogbreeds.data.network.model.Breeds
import com.rifqimfahmi.alldogbreeds.data.network.model.ResRandomDog
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

interface DogApi {
    companion object {
        val ENDPOINT: String = "https://dog.ceo/api/"
    }

    @GET("breeds/list")
    fun getBreedList(): Observable<Breeds>

    @GET("breed/{type}/images")
    fun getBreedImages(@Path("type") breedType: String): Observable<ResBreedImages>

    @GET("breeds/image/random")
    fun getRandomBreed(): Observable<ResRandomDog>
}