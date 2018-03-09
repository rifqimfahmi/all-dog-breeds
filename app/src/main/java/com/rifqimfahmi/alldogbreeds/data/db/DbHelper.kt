package com.rifqimfahmi.alldogbreeds.data.db

import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.data.network.model.giphy.ResRandomMeme
import io.realm.Realm

/*
 * Created by Rifqi Mulya Fahmi on 23/02/18.
 */

interface DbHelper {
    fun saveLovedDog(breed: String, link: String, onSuccess: Realm.Transaction.OnSuccess)
    fun isLoved(link: String): Boolean
    fun removeLovedDog(link: String, onSuccess: Realm.Transaction.OnSuccess)
    fun queryFavoriteDog(): ArrayList<Dog>
    fun saveLovedMeme(resRandomResponse: ResRandomMeme, onSuccess: Realm.Transaction.OnSuccess)
    fun saveLovedMeme(dogMeme: DogMeme, onSuccess: Realm.Transaction.OnSuccess)
    fun isMemeLoved(mResRandomResponse: ResRandomMeme): Boolean
    fun isMemeLoved(dogMeme: DogMeme): Boolean
    fun removeLovedMeme(mResRandomResponse: ResRandomMeme, onSuccess: Realm.Transaction.OnSuccess)
    fun removeLovedMeme(dogMeme: DogMeme, onSuccess: Realm.Transaction.OnSuccess)
    fun queryFavoriteMemes(): ArrayList<DogMeme>
}