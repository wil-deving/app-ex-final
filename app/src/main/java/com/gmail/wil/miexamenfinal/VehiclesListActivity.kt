package com.gmail.wil.miexamenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.wil.miexamenfinal.adapter.VehiclesAdapter
import com.gmail.wil.miexamenfinal.configApp.ConfigServices
import com.gmail.wil.miexamenfinal.model.VehicleResponse
import com.gmail.wil.miexamenfinal.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_vehicles_list.*

class VehiclesListActivity : AppCompatActivity() {

    val retrofitService = ConfigServices().getRetrofit()
    val service = retrofitService.create<ApiInterface>(ApiInterface::class.java)
    var listaVehiclesAdapter: VehiclesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_list)
        listaVehiclesAdapter = VehiclesAdapter(this)
        rvVehicles.adapter = listaVehiclesAdapter
        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.setHasFixedSize(true)
        obtenerListaVehiculos()
    }

    fun obtenerListaVehiculos () {
        // Creamos la varible de servicio del tipo o clase Interface que tengamos
        service.getVehiclesList().enqueue(object : Callback<VehicleResponse> {
            override fun onResponse(call: Call<VehicleResponse>, response: Response<VehicleResponse>) {
                val resp = response!!.body()
                if (resp!!.correcto) {
                    // Toast.makeText(applicationContext,"Peticion Correcta",Toast.LENGTH_SHORT).show()
                    val listaVehicles = resp!!.listaResultado
                    listaVehiclesAdapter!!.updateVehiclesList(listaVehicles)
                } else {
                    Toast.makeText(applicationContext,"Peticion de servicio Errada", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<VehicleResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al realizar el servicio", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
