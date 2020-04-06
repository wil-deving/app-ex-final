package com.gmail.wil.miexamenfinal.configApp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigServices {

    private val PUBLICA = true

    private val protocol = "http"
    private val host = "192.168.0.105"
    private val port = "80"
    private val path = "ShopCar/MobileServices"
    private val pathImages = "ShopCar/Files/Images/"
    // private val path = "ShopCar/testServices"
    // data para obtener datos del backend de la nube
    private val protocolCloud = "https"
    private val hostCloud = "examenfinalwil.000webhostapp.com"
    private val pathCloud = "MobileServices"
    private val pathImagesCloud = "Files/Images/"

    fun getUrlService () : String {
        var URL = protocol + "://" + host + ":" + port + "/" + path + "/"
        if (PUBLICA) {
            URL = protocolCloud + "://" + hostCloud + "/" + pathCloud + "/"
        }
        return URL
    }

    fun getUrlVehiclesImages () : String {
        var URLImages = protocol + "://" + host + ":" + port + "/" + pathImages + "/"
        if (PUBLICA) {
            URLImages = protocolCloud + "://" + hostCloud + "/" + pathImagesCloud + "/"
        }
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