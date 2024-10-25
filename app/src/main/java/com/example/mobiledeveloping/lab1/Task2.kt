package com.example.mobiledeveloping.lab1
import kotlin.random.Random

fun main()
{
    val randomValue = Random.nextInt(-21, 21)
    println("Случайное число: $randomValue")

    val result = if (randomValue in -10..10)
    {
        randomValue + 5
    } else {
        randomValue - 10
    }

    println("Результат: $result")
}