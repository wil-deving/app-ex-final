package com.gmail.wil.miexamenfinal.service

import com.gmail.wil.miexamenfinal.model.SaveQuoteResponse
import com.gmail.wil.miexamenfinal.model.VehicleResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    // El metodo por el cual se hara la peticion, en este caso GET (el endpoint del WS)
    // @GET("random")
    //Endpoint que listara los vehiculos
    @GET("VehiclesList")
    // Retornara un Call del tipo que indiquemos <ObjetoEjemplo>
    /* ObjetoEjemplo debe coincidir la respuesta del servicio o tener una serializacion,
    es decir, si json del ws nos devuelve "id", "name"; este objeto debe tener id, name
    como variables de su clase
    *  */
    fun getVehiclesList() : Call<VehicleResponse>

    //TODO por mas que se envie POST en el back llega GET
    @POST("SaveQuote")
    fun saveQuote(
        @Query("idVehicle") idVehicle:String,
        @Query("tipoCompra") tipoCompra:String,
        @Query("montoInicial") montoInicial:Double,
        @Query("tiempo") tiempo:String,
        @Query("pagoMensual") pagoMensual:Double
    ) : Call<SaveQuoteResponse>

}

