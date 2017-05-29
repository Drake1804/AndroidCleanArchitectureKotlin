package com.drake1804.androidcleanarchitecturekotlin.data.rest

import com.drake1804.androidcleanarchitecturekotlin.data.db.UserEntity
import io.realm.annotations.PrimaryKey

/**
 * Created by drake1804 on 5/19/17.
 */
data class UserModel(@PrimaryKey val id: Int = 0,
                     val name: String = "",
                     val username: String = "",
                     val email: String = "",
                     val address: AddressModel,
                     val phone: String = "",
                     val website: String = "",
                     val company: CompanyModel = CompanyModel()) {

    object Mapper {
        fun from(userEntity: UserEntity) =
                UserModel(userEntity.id, userEntity.name, userEntity.username, userEntity.email,
                        AddressModel(userEntity.addressEntity.street, userEntity.addressEntity.suite, userEntity.addressEntity.city,
                                userEntity.addressEntity.zipcode, GeoModel(userEntity.addressEntity.geoEntity.lat, userEntity.addressEntity.geoEntity.lng)),
                        userEntity.phone, userEntity.website, CompanyModel(userEntity.companyEntity.name, userEntity.companyEntity.catchPhrase, userEntity.companyEntity.bs))
    }
}

data class GeoModel(val lat: Double = 0.0,
                    val lng: Double = 0.0)

data class CompanyModel(val name: String = "",
                        val catchPhrase: String = "",
                        val bs: String = "")

data class AddressModel(val street: String = "",
                        val suite: String = "",
                        val city: String = "",
                        val zipcode: String = "",
                        val geo: GeoModel = GeoModel())