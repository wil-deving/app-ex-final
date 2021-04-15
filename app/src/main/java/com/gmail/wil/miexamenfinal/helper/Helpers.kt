package com.gmail.wil.miexamenfinal.helper

fun randomAlphanumericString(): String {
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    val outputStrLength = (1..36).shuffled().first()
    return (1..outputStrLength)
        .map{ kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}