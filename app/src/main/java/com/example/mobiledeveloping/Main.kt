package com.example.mobiledeveloping

fun main()
{
    print("Введите число A = ")
    val a = readLine()?.toIntOrNull() ?: return
    print("Введите число B = ")
    val b = readLine()?.toIntOrNull() ?: return
    val sum = a+b
    val mult = a*b
    println("Cумма чисел A и B = $sum")
    println("Произведение чисел A и B = $mult")
}