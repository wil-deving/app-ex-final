package com.gmail.wil.miexamenfinal.model

import com.google.gson.annotations.SerializedName
// Debe heredar de Serializable para enviar el objeto completo en intents
import java.io.Serializable

data class Vehicle(
    @SerializedName("id_vehicle") var id_vehicle:String,
    @SerializedName("name_trademark") var name_trademark:String,
    @SerializedName("name_sub_trademark") var name_sub_trademark:String,
    @SerializedName("anio") var anio:String,
    @SerializedName("capacidad") var capacidad:String,
    @SerializedName("img") var img:String,
    @SerializedName("precio_unitario") var precio_unitario:String
) : Serializable