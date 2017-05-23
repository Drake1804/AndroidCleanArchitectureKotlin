package com.drake1804.androidcleanarchitecturekotlin.data.rest

import io.realm.annotations.PrimaryKey

/**
 * Created by drake1804 on 5/19/17.
 */
data class UserModel(@PrimaryKey var id: Int = 0,
                     var name: String = "",
                     var username: String = "",
                     var email: String = "",
                     var address: AddressModel,
                     var phone: String = "",
                     var website: String = "",
                     var company: CompanyModel = CompanyModel())

data class GeoModel(var lat: Double = 0.0,
                    var lng: Double = 0.0)

data class CompanyModel(var name: String = "",
                        var catchPhrase: String = "",
                        var bs: String = "")

data class AddressModel(var street: String = "",
                        var suite: String = "",
                        var city: String = "",
                        var zipcode: String = "",
                        var geo: GeoModel = GeoModel())