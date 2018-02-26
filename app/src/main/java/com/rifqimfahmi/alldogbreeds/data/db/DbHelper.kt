package com.rifqimfahmi.alldogbreeds.data.db

import io.realm.Realm

/*
 * Created by Rifqi Mulya Fahmi on 23/02/18.
 */

interface DbHelper {
    fun saveLovedDog(breed: String, link: String, onSuccess: Realm.Transaction.OnSuccess)
    fun isLoved(link: String): Boolean
    fun removeLovedDog(link: String, onSuccess: Realm.Transaction.OnSuccess)
}