package com.drake1804.androidcleanarchitecturekotlin.data.db

import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmList
import timber.log.Timber

/**
 * Created by drake1804 on 5/19/17.
 */

class DbService {

    fun getUsers(): Observable<List<UserModel>> {
        val realm = Realm.getDefaultInstance()
        val userEntities = realm.where(UserEntity::class.java).findAll()
        val usersList = convert(userEntities)
        Timber.tag(DbService::class.java.simpleName).d("get users %d", usersList.size)
        realm.close()

        return Observable.just(usersList)
    }

    fun saveUsers(userModels: List<UserModel>) {
        val realm = Realm.getDefaultInstance()
        val userEntities = RealmList<UserEntity>()
        userModels.mapTo(userEntities) { UserEntity.Mapper.from(it) }
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(userEntities) }
        realm.close()
    }

    fun convert(userEntities: List<UserEntity>): ArrayList<UserModel> {
        val users = arrayListOf<UserModel>()
        userEntities.mapTo(users) { UserModel.Mapper.from(it) }
        return users
    }
}