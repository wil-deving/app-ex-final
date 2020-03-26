package com.gmail.wil.miexamenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnQuote.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            btnQuote.id -> abrirActivityList()
        }
    }

    fun abrirActivityList () {
        print("Presiono boton")
        //val intent = Intent(this, ListActivity::class.java)
        val intent = Intent(this, VehiclesListActivity::class.java)
        startActivity(intent)
    }

}
