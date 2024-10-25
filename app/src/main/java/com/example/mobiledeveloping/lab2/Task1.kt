package com.example.mobiledeveloping.lab2

fun task1(number: Int): Int
{
    return if (number % 2 != 0) {
        0
    } else {
        number * number
    }
}

fun main()
{
    println(task1(7))
    println(task1(2))
}