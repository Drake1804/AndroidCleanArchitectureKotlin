package com.drake1804.androidcleanarchitecturekotlin.data.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by drake1804 on 5/19/17.
 */

open class GeoEntity : RealmObject() {
    var lat: Double = 0.0
    var lng: Double = 0.0
}

open class CompanyEntity : RealmObject() {
    var name: String = ""
    var catchPhrase: String = ""
    var bs: String = ""
}

open class AddressEntity : RealmObject() {
    var street: String = ""
    var suite: String = ""
    var city: String = ""
    var zipcode: String = ""
    var geoEntity: GeoEntity = GeoEntity()
}

open class UserEntity : RealmObject() {
    @PrimaryKey var id: Int = 0
    var name: String = ""
    var username: String = ""
    var email: String = ""
    var addressEntity: AddressEntity = AddressEntity()
    var phone: String = ""
    var website: String = ""
    var companyEntity: CompanyEntity = CompanyEntity()
}