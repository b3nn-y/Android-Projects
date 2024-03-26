package com.bennysamuel.whatsmynextcountry.countryInfoRetrofit

import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("name")
    val name:Name,
    @SerializedName("currencies")
    val currencies: Map<String, CurrencyName>,
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("region")
    val region:String,
    @SerializedName("maps")
    val maps: Maps,
    @SerializedName("flags")
    val flags: Flags

) {
}

data class Name(
    val common:String
){

}
data class CurrencyName(
    val name: String
)

data class Maps(
    val googleMaps: String
)
data class Flags(
    val png: String,
    val svg: String
)
