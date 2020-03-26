package com.gmail.wil.miexamenfinal.configApp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigServices {

    private val protocol = "http"
    private val host = "192.168.0.105"
    private val port = "80"
    private val path = "ShopCar/MobileServices"
    private val pathImages = "ShopCar/Files/Images/"
    // private val path = "ShopCar/testServices"

    fun getUrlService () : String {
        val URL = protocol + "://" + host + ":" + port + "/" + path + "/"
        return URL
    }

    fun getUrlVehiclesImages () : String {
        val URLImages = protocol + "://" + host + ":" + port + "/" + pathImages + "/"
        return URLImages
    }

    /*
        * TODO Se agregaron librerias de retrofit, etc para hacer esta peticion,
        * TODO ademas de permisos en el manifest.
    */

    fun getRetrofit () : Retrofit {
        // Creamos una variable de retrofit
        val retrofit: Retrofit = Retrofit.Builder()
            /* se pasa la url Base donde haremos la peticion, los endpoints van
            en la clase interface */
            //.baseUrl("https://dog.ceo/api/breeds/image/")
            .baseUrl(getUrlService())
            // La respuesta se convertira de JSON a un objeto Kotilin(JAVA)
            .addConverterFactory(GsonConverterFactory.create())
            //iniciar
            .build()
        return retrofit
    }

}