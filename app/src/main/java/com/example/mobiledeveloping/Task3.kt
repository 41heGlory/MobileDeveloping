package com.example.mobiledeveloping

import kotlin.random.Random

fun main()
{
    val randomValue = Random.nextInt(10, 101)
    println("Случайное натуральное число: $randomValue")
    for(i in 1..100)
    {
        if(randomValue / 10 == i % 10 && randomValue % 10 == i / 10) {
        println("Обратное чилсло: $i")
        }
    }
}