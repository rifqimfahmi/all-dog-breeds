package com.rifqimfahmi.alldogbreeds.data.db

import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import com.rifqimfahmi.alldogbreeds.data.network.model.giphy.ResRandomMeme
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 23/02/18.
 */
 
class AppDbHelper @Inject constructor() : DbHelper {

    var mRealm = Realm.getDefaultInstance()

    init {
        mRealm.close()
    }

    override fun saveLovedMeme(resRandomResponse: ResRandomMeme, onSuccess: Realm.Transaction.OnSuccess) {
        initRealmInstance()

        val meme = resRandomResponse.data?.get(0)

        val dogMeme = DogMeme(
                meme?.slug,
                meme?.giphySource,
                meme?.shortLink,
                meme?.images?.original?.url,
                meme?.images?.downsized_medium?.url,
                meme?.images?.preview?.url
        )
        mRealm.executeTransactionAsync(Realm.Transaction {
            it.copyToRealm(dogMeme)
        }, onSuccess)

        closeRealm()
    }

    override fun saveLovedMeme(dogMeme: DogMeme, onSuccess: Realm.Transaction.OnSuccess) {
        initRealmInstance()

        mRealm.executeTransactionAsync(Realm.Transaction {
            it.copyToRealm(dogMeme)
        }, onSuccess)

        closeRealm()
    }

    override fun queryFavoriteMemes(): ArrayList<DogMeme> {
        initRealmInstance()

        val dogs = mRealm.where(DogMeme::class.java).findAll()
        val list = mRealm.copyFromRealm(dogs)

        closeRealm()

        return list as ArrayList<DogMeme>
    }

    override fun queryFavoriteDog(): ArrayList<Dog> {
        initRealmInstance()

        val dogs = mRealm.where(Dog::class.java)
                .sort("breed", Sort.ASCENDING, "date", Sort.ASCENDING).findAll()
        val list = mRealm.copyFromRealm(dogs)

        closeRealm()

        return list as ArrayList<Dog>
    }

    override fun isMemeLoved(mResRandomResponse: ResRandomMeme): Boolean {
        initRealmInstance()

        val slug = mResRandomResponse.data?.get(0)?.slug
        val sizeCount = mRealm.where(DogMeme::class.java).equalTo("slug", slug).findAll().size

        closeRealm()
        return sizeCount > 0
    }

    override fun isMemeLoved(dogMeme: DogMeme): Boolean {
        initRealmInstance()

        val slug = dogMeme.slug
        val sizeCount = mRealm.where(DogMeme::class.java).equalTo("slug", slug).findAll().size

        closeRealm()
        return sizeCount > 0
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

    override fun removeLovedMeme(mResRandomResponse: ResRandomMeme, onSuccess: Realm.Transaction.OnSuccess) {
        initRealmInstance()

        mRealm.executeTransactionAsync(Realm.Transaction {
            val slug = mResRandomResponse.data?.get(0)?.slug
            val realmResult: RealmResults<DogMeme> = it.where(DogMeme::class.java).equalTo("slug", slug).findAll()
            realmResult.deleteAllFromRealm()
        }, onSuccess)

        closeRealm()
    }

    override fun removeLovedMeme(dogMeme: DogMeme, onSuccess: Realm.Transaction.OnSuccess) {
        initRealmInstance()

        mRealm.executeTransactionAsync(Realm.Transaction {
            val slug = dogMeme.slug
            val realmResult: RealmResults<DogMeme> = it.where(DogMeme::class.java).equalTo("slug", slug).findAll()
            realmResult.deleteAllFromRealm()
        }, onSuccess)

        closeRealm()
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