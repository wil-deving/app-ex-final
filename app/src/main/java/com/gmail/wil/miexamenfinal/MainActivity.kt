package com.gmail.wil.miexamenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.net.DnsResolver
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gmail.wil.miexamenfinal.adapter.OffersAdapter
import com.gmail.wil.miexamenfinal.configApp.ConfigServices
import com.gmail.wil.miexamenfinal.model.dto.OffersResponseDto
import com.gmail.wil.miexamenfinal.service.OfferApiInterface

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    val retrofitClient = ConfigServices().getRetrofitClient()
    val offersService = retrofitClient.create<OfferApiInterface>(OfferApiInterface::class.java)
    var offerListAdapter: OffersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshProductList)

        offerListAdapter = OffersAdapter(this)
        rvOffers.adapter = offerListAdapter
        rvOffers.layoutManager = LinearLayoutManager(this)
        rvOffers.setHasFixedSize(true)
        getOffersData()

        swipeRefreshLayout.setOnRefreshListener {
            getOffersData()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun getOffersData() {
        offersService.getOfferList().enqueue(object : Callback<OffersResponseDto> {
            override fun onResponse(
                call: Call<OffersResponseDto>,
                response: Response<OffersResponseDto>
            ) {
                val resp = response!!.body()
                if (resp!!.correct) {
                    val offerList = resp!!.data
                    offerListAdapter!!.updateOffersList(offerList)
                    tvOffersAmount.text = offerListAdapter!!.getItemCount().toString() + " Registros"
                } else {
                    Toast.makeText(applicationContext,
                        "Error al realizar el servicio",
                        Toast.LENGTH_SHORT).show()
                    swipeRefreshLayout.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<OffersResponseDto>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "Error al realizar el servicio",
                    Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

}
