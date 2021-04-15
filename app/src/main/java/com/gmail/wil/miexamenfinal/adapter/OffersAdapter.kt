package com.gmail.wil.miexamenfinal.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.wil.miexamenfinal.OfferDetail
import com.gmail.wil.miexamenfinal.R
import com.gmail.wil.miexamenfinal.model.Offer

class OffersAdapter(private val context: Context) : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    private val dataSetAdp: ArrayList<Offer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.offer_item, parent, false)
        return OfferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val itemActual = dataSetAdp[position]
        holder.titleOffer.text = itemActual.description
        holder.city.text = "Ciudad: " + itemActual.city
        holder.address.text = "Dirección: " + itemActual.address
    }

    override fun getItemCount () = dataSetAdp.size

    fun updateOffersList (offers: ArrayList<Offer>) {
        dataSetAdp.addAll(offers)
        notifyDataSetChanged()
    }

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleOffer = itemView.findViewById(R.id.tvTitleItem) as TextView
        val city = itemView.findViewById(R.id.tvFirstText) as TextView
        val address = itemView.findViewById(R.id.tvSecondText) as TextView
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, OfferDetail::class.java)
                intent.putExtra("offer", dataSetAdp[adapterPosition])
                context.startActivity(intent)
            }
        }
    }

}