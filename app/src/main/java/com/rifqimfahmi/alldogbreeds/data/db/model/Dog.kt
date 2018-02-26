package com.rifqimfahmi.alldogbreeds.data.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/*
 * Created by Rifqi Mulya Fahmi on 23/02/18.
 */

open class Dog : RealmObject {

    var breed: String? = null
    var link: String? = null
    var date: Date = Date()

    constructor() {}

    constructor(mBreed: String, mLink: String) {
        this.breed = mBreed
        this.link = mLink
    }
}