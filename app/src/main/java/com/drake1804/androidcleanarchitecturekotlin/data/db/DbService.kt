package com.drake1804.androidcleanarchitecturekotlin.data.db

import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import timber.log.Timber

/**
 * Created by drake1804 on 5/19/17.
 */

class DbService {

    fun getUsers(): Observable<List<UserModel>> {
        val realm: Realm = Realm.getDefaultInstance()
        val userEntities: RealmResults<UserEntity> = realm.where(UserEntity::class.java).findAll()
        val usersList: ArrayList<UserModel> = arrayListOf()
        usersList.addAll(convert(userEntities))
        Timber.tag(DbService::class.java.simpleName).d("get users %d", usersList.size)
        realm.close()

        return Observable.just(usersList)
    }

    fun saveUsers(userModels: List<UserModel>) {
        val realm: Realm = Realm.getDefaultInstance()
        val userEntities: RealmList<UserEntity> = RealmList()
        userModels.mapTo(userEntities) { Mapper.mapUser(it) }
        realm.executeTransaction{ realm1 -> realm1.copyToRealmOrUpdate(userEntities) }
        realm.close()
    }


    fun convert(userEntities: List<UserEntity>): List<UserModel> {
        val users: ArrayList<UserModel> = arrayListOf()
        userEntities.mapTo(users) { Mapper.mapUser(it) }
        return users
    }
}