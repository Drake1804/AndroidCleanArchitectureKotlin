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
        var usersList: List<UserModel> = ArrayList()
        usersList.plus(convert(userEntities))
        Timber.tag(DbService::class.simpleName).d("get users %d", usersList.size)
        realm.close()

        return Observable.just(usersList)
    }

    fun saveUsers(userModels: List<UserModel>) {
        val realm: Realm = Realm.getDefaultInstance()
        var userEntities: RealmList<UserEntity> = RealmList()
        for(userModel in userModels) {
            userEntities.plus(Mapper.mapUser(userModel))
        }
        realm.executeTransaction({
             realm1 -> realm1.copyToRealmOrUpdate(userEntities)
        })
        realm.close()
    }


    fun convert(userEntities: List<UserEntity>) {
        var users: List<UserModel> = ArrayList()
        for(userEntity in userEntities) {
            users.plus(Mapper.mapUser(userEntity))
        }
    }


}