package com.drake1804.androidcleanarchitecturekotlin.data.db

import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
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

    object Mapper {
        fun from(userModel: UserModel): UserEntity {
            val geoEntity = GeoEntity()
            geoEntity.lat = userModel.address.geo.lat
            geoEntity.lng = userModel.address.geo.lng

            val addressEntity = AddressEntity()
            addressEntity.street = userModel.address.street
            addressEntity.suite = userModel.address.suite
            addressEntity.city = userModel.address.city
            addressEntity.zipcode = userModel.address.zipcode
            addressEntity.geoEntity = geoEntity

            val companyEntity = CompanyEntity()
            companyEntity.name = userModel.company.name
            companyEntity.catchPhrase = userModel.company.catchPhrase
            companyEntity.bs = userModel.company.bs

            val userEntity = UserEntity()
            userEntity.id = userModel.id
            userEntity.name = userModel.name
            userEntity.username = userModel.username
            userEntity.email = userModel.email
            userEntity.addressEntity = addressEntity
            userEntity.phone = userModel.phone
            userEntity.website = userModel.website
            userEntity.companyEntity = companyEntity

            return userEntity
        }
    }
}

