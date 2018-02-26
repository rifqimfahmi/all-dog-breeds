package com.rifqimfahmi.alldogbreeds.data.db

import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 23/02/18.
 */
 
class AppDbHelper @Inject constructor() : DbHelper {

    var mRealm = Realm.getDefaultInstance()

    init {
        mRealm.close()
    }

    override fun saveLovedDog(breed: String, link: String, onSuccess: Realm.Transaction.OnSuccess) {
        initRealmInstance()

        val dog = Dog(breed, link)
        mRealm.executeTransactionAsync(Realm.Transaction {
            it.copyToRealm(dog)
        }, onSuccess)

        closeRealm()
    }

    override fun isLoved(link: String): Boolean {
        initRealmInstance()

        val sizeCount = mRealm.where(Dog::class.java).equalTo("link", link).findAll().size

        closeRealm()
        return sizeCount > 0
    }

    override fun removeLovedDog(link: String, onSuccess: Realm.Transaction.OnSuccess) {
        initRealmInstance()

        mRealm.executeTransactionAsync(Realm.Transaction {
            val realmResult: RealmResults<Dog> = it.where(Dog::class.java).equalTo("link", link).findAll()
            realmResult.deleteAllFromRealm()
        }, onSuccess)

        closeRealm()
    }

    private fun initRealmInstance() {
        mRealm.close()
        mRealm = Realm.getDefaultInstance()
    }

    private fun closeRealm() {
        mRealm.close()
    }

}