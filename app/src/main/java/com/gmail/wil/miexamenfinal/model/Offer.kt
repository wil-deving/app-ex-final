package com.gmail.wil.miexamenfinal.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Offer(
    @SerializedName("offerId") var offerId: String,
    @SerializedName("city") var city: String,
    @SerializedName("address") var address: String,
    @SerializedName("description") var description: String,
    @SerializedName("surface") var surface: Double,
    @SerializedName("buildedSurface") var buildedSurface: Double,
    @SerializedName("offerPrice") var offerPrice: Double
) : Serializable