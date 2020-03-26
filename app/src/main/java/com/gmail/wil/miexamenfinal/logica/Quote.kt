package com.gmail.wil.miexamenfinal.logica

import java.math.RoundingMode
import java.text.DecimalFormat

fun obtenerCuotaMensual (capitalInicial:Double, capitalAmortizado:Double, anios:String) : Double {
    val tazaBsToSus = 6.96
    val interesAnual = 9.00
    // por 12 meses de cada año
    val tiempo = tiempoMeses.invoke(anios.toInt())

    val interesMensual = interesMensual.invoke(interesAnual)
    val i = interesMensual / 100
    val aux1 = 1 + i
    val aux2 = Math.pow(aux1, tiempo.toDouble())
    val saldoCapital = (capitalInicial * tazaBsToSus) - (capitalAmortizado * tazaBsToSus)

    var aux4 = aux2 - 1
    var aux3 = saldoCapital * aux2 * i

    val cuotaBrutaMes = aux3 / aux4
    // redondeando a 2 decimales
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    val cuotaNetaMes = df.format(cuotaBrutaMes)
    return cuotaNetaMes.toDouble()
}

var tiempoMeses = { anios:Int -> anios * 12 }
var interesMensual = { interesAnual:Double -> interesAnual / 12 }