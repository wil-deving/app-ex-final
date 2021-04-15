package com.gmail.wil.miexamenfinal.service

import com.gmail.wil.miexamenfinal.model.dto.OffersResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface OfferApiInterface {

    @GET("offer/offer-to-app")
    fun getOfferList() : Call<OffersResponseDto>
}