package com.gmail.wil.miexamenfinal.model

import com.google.gson.annotations.SerializedName

data class SaveQuoteResponse (
    @SerializedName("correcto") var correcto:Boolean,
    @SerializedName("mensaje") var mensaje:String,
    @SerializedName("listaResultado") var listaResultado:ArrayList<Vehicle>
)