package com.gmail.wil.miexamenfinal.configApp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigServices {

    private val API_SERVICES_URL = "http://192.168.0.109:8011/v1/"

    fun getRetrofitClient() : Retrofit {
        // Creamos una variable de retrofit
        val retrofit: Retrofit = Retrofit.Builder()
            /* se pasa la url Base donde haremos la peticion, los endpoints van
            en la clase interface */
            //.baseUrl("https://dog.ceo/api/breeds/image/")
            .baseUrl(API_SERVICES_URL)
            // La respuesta se convertira de JSON a un objeto Kotilin(JAVA)
            .addConverterFactory(GsonConverterFactory.create())
            //iniciar
            .build()
        return retrofit
    }

}