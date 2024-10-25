package com.example.mobiledeveloping.lab2

val task2:(Int) -> Boolean = {
    number -> number in 100..999
}

fun main()
{
    println(task2(7))
    println(task2(777))
}