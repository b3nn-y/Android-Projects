package com.bennysamuel.whatsmynextcountry.recyclerview

import android.os.Parcel
import android.os.Parcelable

data class CountryRecycleViewData(
    val countryName: String,
    val countryImageLink: String,
    var saved: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(countryName)
        parcel.writeString(countryImageLink)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryRecycleViewData> {
        override fun createFromParcel(parcel: Parcel): CountryRecycleViewData {
            return CountryRecycleViewData(parcel)
        }

        override fun newArray(size: Int): Array<CountryRecycleViewData?> {
            return arrayOfNulls(size)
        }
    }
}