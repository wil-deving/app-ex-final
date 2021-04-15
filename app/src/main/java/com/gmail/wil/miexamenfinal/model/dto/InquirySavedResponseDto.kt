package com.gmail.wil.miexamenfinal.model.dto

import com.gmail.wil.miexamenfinal.model.Offer
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class InquirySavedResponseDto (
    @SerializedName("inquirieId") var inquirieId: String,
    @SerializedName("offerId") var offerId: String,
    @SerializedName("interestedId") var interestedId: String,
    @SerializedName("createdAt") var createdAt: Date
) : Serializable