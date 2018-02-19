package com.rifqimfahmi.alldogbreeds.data.network

import com.rifqimfahmi.alldogbreeds.data.network.model.Breeds
import io.reactivex.Observable
import retrofit2.http.GET

/*
 * Created by Rifqi Mulya Fahmi on 18/02/18.
 */

interface DogApi {
    companion object {
        val ENDPOINT: String = "https://dog.ceo/api/"
    }

    @GET("breeds/list")
    fun getBreedList(): Observable<Breeds>
}