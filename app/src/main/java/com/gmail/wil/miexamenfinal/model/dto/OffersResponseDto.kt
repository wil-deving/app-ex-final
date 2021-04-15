package com.gmail.wil.miexamenfinal.model.dto

import com.gmail.wil.miexamenfinal.model.Offer
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

class OffersResponseDto(
    @SerializedName("correct") var correct: Boolean,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<Offer>
) : Serializable