package com.example.mobiledeveloping

fun main()
{
    val matrix = arrayOf(
        arrayOf(1, -2, 0, 4),
        arrayOf(-1, 0, 3, -4),
        arrayOf(0, 2, -5, 6),
        arrayOf(7, 8, 0, -9)
    )

    var positiveCount = 0
    var negativeCount = 0

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            when {
                matrix[i][j] > 0 -> positiveCount++
                matrix[i][j] < 0 -> negativeCount++
                matrix[i][j] == 0 -> println("Нулевой элемент найден в координатах: (${i + 1}, ${j + 1})")
            }
        }
    }

    println("Количество положительных элементов: $positiveCount")
    println("Количество отрицательных элементов: $negativeCount")
}