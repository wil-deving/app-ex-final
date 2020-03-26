package com.gmail.wil.miexamenfinal

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmail.wil.miexamenfinal.configApp.ConfigServices
import com.gmail.wil.miexamenfinal.logica.obtenerCuotaMensual
import com.gmail.wil.miexamenfinal.model.SaveQuoteResponse
import com.gmail.wil.miexamenfinal.model.Vehicle
import com.gmail.wil.miexamenfinal.service.ApiInterface
import retrofit2.Callback
import kotlinx.android.synthetic.main.activity_quote_vehicle.*
import retrofit2.Call
import retrofit2.Response

class QuoteVehicleActivity : AppCompatActivity() {

    val retrofitService = ConfigServices().getRetrofit()
    val service = retrofitService.create<ApiInterface>(ApiInterface::class.java)
    lateinit var auto: Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_vehicle)

        auto = intent.getSerializableExtra("auto") as Vehicle
        tvTitleVehicle.text = auto.name_trademark + " " + auto.name_sub_trademark
        tvPrecio.text = auto.precio_unitario

        btnCalcularQuote.setOnClickListener {
            // Enviar quote WS asincronamente
            AsyncTaskExample().execute()
        }

        rbCredito.setOnClickListener {
            // Toast.makeText(this, "Presiono credito", Toast.LENGTH_SHORT).show()
            onClickRadioCredito()
        }

        rbContado.setOnClickListener {
            // Toast.makeText(this, "Presiono contado", Toast.LENGTH_SHORT).show()
            onClickRadioContado()
        }

    }

    fun obtenertipoCompra () : String {
        var tipoCompra = ""
        if (rbCredito.isChecked) {
            tipoCompra = "Credito"
        }
        if (rbContado.isChecked) {
            tipoCompra = "Contado"
        }
        return tipoCompra
    }

    fun onClickRadioCredito () {
        tvTagPagoIni.setVisibility(View.VISIBLE)
        etPagoIni.setVisibility(View.VISIBLE)
        tvspnTagAnios.setVisibility(View.VISIBLE)
        spnAnios.setVisibility(View.VISIBLE)
        btnCalcularQuote.setVisibility(View.VISIBLE)
        tvTagMonthQuote.setVisibility(View.VISIBLE)
        tvMonthQuote.setVisibility(View.VISIBLE)
        tvPlazo.setVisibility(View.VISIBLE)
    }

    fun onClickRadioContado () {
        tvTagPagoIni.setVisibility(View.INVISIBLE)
        etPagoIni.setVisibility(View.INVISIBLE)
        tvspnTagAnios.setVisibility(View.INVISIBLE)
        spnAnios.setVisibility(View.INVISIBLE)
        btnCalcularQuote.setVisibility(View.INVISIBLE)
        tvTagMonthQuote.setVisibility(View.INVISIBLE)
        tvMonthQuote.setVisibility(View.INVISIBLE)
        tvPlazo.setVisibility(View.INVISIBLE)
        // Enviar quote WS asincronamente
        AsyncTaskExample().execute()
    }

    fun prepareDataForSend () {
        val idAuto = auto.id_vehicle
        val tipoQuote = obtenertipoCompra()
        val capitalInicial = auto.precio_unitario.toDouble()
        val capitalAmortizado = etPagoIni.text.toString().toDouble()
        //dato del spinner
        val AniosList = findViewById(R.id.spnAnios) as Spinner
        val anios = AniosList.selectedItem.toString()
        val cuotaMes = obtenerCuotaMensual(capitalInicial, capitalAmortizado, anios)
        if (tipoQuote.equals("Contado")) {
            enviarDataQuote(idAuto, tipoQuote, 0.00, "0", 0.00)
        } else if (tipoQuote.equals("Credito")) {
            enviarDataQuote(idAuto, tipoQuote, capitalAmortizado, anios, cuotaMes)
        }
    }

    fun setAfterResp (couta: Double, anios:String) {
        tvMonthQuote.text = couta.toString()
        tvPlazo.text = "A ${anios.toInt()} Años plazo"
    }

    fun enviarDataQuote (idAuto:String, tipoQuote:String, capitalAmortizado:Double, anios:String,
                         cuotaMes:Double) {
        service.saveQuote(idAuto, tipoQuote, capitalAmortizado, anios, cuotaMes)
            .enqueue(object : Callback<SaveQuoteResponse> {
                override fun onResponse(call: Call<SaveQuoteResponse>,
                                        response: Response<SaveQuoteResponse>) {
                    val resp = response!!.body()
                    if (resp!!.correcto) {
                        setAfterResp(cuotaMes, anios)
                    } else {
                        Toast.makeText(applicationContext,
                            "Error al ejecutar Guardado",
                            Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<SaveQuoteResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,
                        "Error de petición: $t",
                        Toast.LENGTH_LONG).show()
                    tvPlazo.text = "Error: $t"
                }
            })
    }

    inner class AsyncTaskExample : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String?): String {
            prepareDataForSend()
            return "termino"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            /* Toast.makeText(applicationContext,
                "paso la ultima de async",
                Toast.LENGTH_SHORT).show() */
            progressBar.visibility = View.INVISIBLE
        }
    }

}
