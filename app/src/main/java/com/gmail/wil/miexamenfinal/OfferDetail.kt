package com.gmail.wil.miexamenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gmail.wil.miexamenfinal.configApp.ConfigServices
import com.gmail.wil.miexamenfinal.helper.randomAlphanumericString
import com.gmail.wil.miexamenfinal.model.Offer
import com.gmail.wil.miexamenfinal.model.dto.InquiryDto
import com.gmail.wil.miexamenfinal.model.dto.InquirySavedResponseDto
import com.gmail.wil.miexamenfinal.service.InquiryApiInterface
import kotlinx.android.synthetic.main.activity_offer_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfferDetail : AppCompatActivity() {

    val retrofitClient = ConfigServices().getRetrofitClient()
    val inquiryService = retrofitClient.create<InquiryApiInterface>(InquiryApiInterface::class.java)
    lateinit var offer: Offer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_detail)

        offer = intent.getSerializableExtra("offer") as Offer

        tvTitleOffer.text = offer.description
        tvCityOffer.text = offer.city
        tvAddressOffer.text = offer.address
        tvSurface.text = offer.surface.toString()
        tvBuildedSurface.text = offer.buildedSurface.toString()
        tvPrice.text = offer.offerPrice.toString()

        btnViewFields.setOnClickListener {
            llFields.setVisibility(View.VISIBLE)
        }

        btnGetInquiri.setOnClickListener {
            if (validateFieldsToSend()) {
                sendDataForInquiry()
            }
        }
    }

    fun validateFieldsToSend() : Boolean {
        val interestedName = etInterestedName.text.toString()
        val interestedCellphone = etInterestedPhone.text.toString()
        if (interestedName == null || interestedName == "") {
            Toast.makeText(applicationContext,
                "Nombre Vacío",
                Toast.LENGTH_LONG).show()
            return false
        }
        if (interestedCellphone == null || interestedCellphone == "") {
            Toast.makeText(applicationContext,
                "Campo Celular vacío",
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun sendDataForInquiry() {
        val interestedName = etInterestedName.text.toString()
        val interestedCellphone = etInterestedPhone.text.toString()
        val interestedEmail = etInterestedEmail.text.toString()

        val inquiryDto = InquiryDto(
            randomAlphanumericString(),
            offer.offerId,
            randomAlphanumericString(),
            randomAlphanumericString(),
            interestedName,
            interestedCellphone,
            interestedEmail
        )
        inquiryService.saveInquiry(inquiryDto)
            .enqueue(object : Callback<InquirySavedResponseDto> {
                override fun onResponse(
                    call: Call<InquirySavedResponseDto>,
                    response: Response<InquirySavedResponseDto>
                ) {
                    llFields.setVisibility(View.INVISIBLE)
                    Toast.makeText(applicationContext,
                        "Solicitud de información enviada exitosamente",
                        Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<InquirySavedResponseDto>, t: Throwable) {
                    Toast.makeText(applicationContext,
                        "Error en la solicitud: $t",
                        Toast.LENGTH_LONG).show()
                }
            })
    }

}