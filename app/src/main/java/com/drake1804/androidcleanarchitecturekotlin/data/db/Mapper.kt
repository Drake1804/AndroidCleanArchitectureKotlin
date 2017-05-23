package com.drake1804.androidcleanarchitecturekotlin.data.db

import com.drake1804.androidcleanarchitecturekotlin.data.rest.AddressModel
import com.drake1804.androidcleanarchitecturekotlin.data.rest.CompanyModel
import com.drake1804.androidcleanarchitecturekotlin.data.rest.GeoModel
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel

/**
 * Created by drake1804 on 5/19/17.
 */

open class Mapper {
    companion object {
        @JvmStatic fun mapUser(userEntity: UserEntity): UserModel {
            return UserModel(userEntity.id, userEntity.name, userEntity.username, userEntity.email,
                    AddressModel(userEntity.addressEntity.street, userEntity.addressEntity.suite,
                            userEntity.addressEntity.city, userEntity.addressEntity.zipcode,
                            GeoModel(userEntity.addressEntity.geoEntity.lat,
                            userEntity.addressEntity.geoEntity.lng)),
                    userEntity.phone, userEntity.website, CompanyModel(userEntity.companyEntity.name,
                    userEntity.companyEntity.catchPhrase, userEntity.companyEntity.bs))
        }

        @JvmStatic fun mapUser(userModel: UserModel): UserEntity {
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