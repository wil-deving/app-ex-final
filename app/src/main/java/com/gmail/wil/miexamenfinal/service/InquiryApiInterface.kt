package com.gmail.wil.miexamenfinal.service

import com.gmail.wil.miexamenfinal.model.dto.InquiryDto
import com.gmail.wil.miexamenfinal.model.dto.InquirySavedResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InquiryApiInterface {

    @POST("inquiry")
    fun saveInquiry(@Body inquiryDto: InquiryDto) : Call<InquirySavedResponseDto>

}