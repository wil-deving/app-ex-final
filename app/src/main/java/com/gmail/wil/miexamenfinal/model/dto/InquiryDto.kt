package com.gmail.wil.miexamenfinal.model.dto

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field
import java.io.Serializable

class InquiryDto(
    @SerializedName("inquiryId") var inquiryId: String,
    @SerializedName("offerId") var offerId: String,
    @SerializedName("interestedId") var interestedId: String,
    @SerializedName("peopleId") var peopleId: String,
    @SerializedName("name") var name: String,
    @SerializedName("cellphone") var cellphone: String,
    @SerializedName("email") var email: String
) : Serializable