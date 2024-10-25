package com.example.mobiledeveloping
import kotlin.math.sqrt

fun task1(){
    val variable:Int? = 7
    val text:String? = 9.toString()
    println(text)
    //В консоль вывелось null
    //В консоль вывелось 9
}

fun getFullName(firstName: String?, lastName:String? ){
    val firstname = firstName ?: "Unknown"
    val lastname = lastName ?: "Unknown"
    println("$firstname $lastname")
}

fun calculateSquareRoot(number: Double?):Double
{
    return sqrt(number!!)
}

fun getStringLength(obj:Any?):Any?
{
    val objective = obj as? String
    if (objective == null)
    {
        return -1
    }
    else{
        return objective
    }
}


fun main()
{
    task1()
    getFullName(null, "Платонов")
    println(calculateSquareRoot(0.0))
    println(getStringLength("null"))
}
