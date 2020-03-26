package com.gmail.wil.miexamenfinal.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.wil.miexamenfinal.QuoteVehicleActivity
import com.gmail.wil.miexamenfinal.R
import com.gmail.wil.miexamenfinal.configApp.ConfigServices
import com.gmail.wil.miexamenfinal.model.Vehicle

class VehiclesAdapter(private val context: Context) : RecyclerView.Adapter<VehiclesAdapter.VehicleViewHolder>() {

    private val urlImages = ConfigServices().getUrlVehiclesImages()
    private val dataSetAdp: ArrayList<Vehicle> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehicle_item, parent, false)
        return VehicleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val itemActual = dataSetAdp[position]
        holder.titleVehicle.text = itemActual.name_trademark + " " + itemActual.name_sub_trademark
        holder.anioVehicle.text = "Modelo: " + itemActual.anio
        holder.capacidadVehicle.text = "Capacidad pasajeros: " + itemActual.capacidad
        Glide.with(context)
            .load(urlImages + itemActual.img)
            .into(holder.imgVehicle)
    }

    override fun getItemCount () = dataSetAdp.size

    fun updateVehiclesList (listaVehicles: ArrayList<Vehicle>) {
        dataSetAdp.addAll(listaVehicles)
        notifyDataSetChanged()
    }

    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVehicle = itemView.findViewById(R.id.ivImgItemVeh) as ImageView
        val titleVehicle = itemView.findViewById(R.id.tvTitleItemVe) as TextView
        val anioVehicle = itemView.findViewById(R.id.tvAnioItemVe) as TextView
        val capacidadVehicle = itemView.findViewById(R.id.tvCapacityItemVe) as TextView
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, QuoteVehicleActivity::class.java)
                intent.putExtra("auto", dataSetAdp[adapterPosition])
                context.startActivity(intent)
            }
        }
    }

}