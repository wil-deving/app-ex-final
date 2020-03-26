package com.gmail.wil.miexamenfinal.model
import com.google.gson.annotations.SerializedName

// El objeto debe coincidir con las propiedades de respuesta JSON del WS
data class VehicleResponse (
    @SerializedName("correcto") var correcto:Boolean,
    @SerializedName("mensaje") var mensaje:String,
    @SerializedName("listaResultado") var listaResultado:ArrayList<Vehicle>
)