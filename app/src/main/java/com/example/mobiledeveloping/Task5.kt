package com.example.mobiledeveloping

abstract class Shape(val name: String, val color: String) {
    abstract fun area(): Double

    fun info() {
        println("Фигура: $name, Цвет: $color, Площадь: ${area()}")
    }
}

class Circle(val radius: Double, color: String) : Shape("Круг", color) {
    override fun area(): Double {
        return Math.PI * radius * radius
    }
}

class Rectangle(val width: Double, val height: Double, color: String) : Shape("Прямоугольник", color) {
    override fun area(): Double {
        return width * height
    }
}

fun main() {
    val circle = Circle(5.0, "Красный")
    val rectangle = Rectangle(4.0, 6.0, "Синий")

    circle.info()
    rectangle.info()
}