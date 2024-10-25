package com.example.mobiledeveloping.lab2

fun Array<Int>.filterAndMap(filter: (Int) -> Boolean , map: (Int) -> Int): Array<Int> {
    return this.filter(filter).map(map).toTypedArray()
}

fun main() {
    val array = arrayOf(1, 222, 3, 444, 5, 6, 7, 878, 9, 10)

    val result = array.filterAndMap(task2, ::task1)

    println(result.joinToString(", ")) // Вывод: 4, 8, 12, 16, 20
}