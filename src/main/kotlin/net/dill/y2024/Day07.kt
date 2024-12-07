package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day07: Day() {
    override val data by lazy { resourceLines(2024, 7) }

    override fun part1(): Long {
        val calibrationResults = mutableListOf<Long>()
        data.forEach { line ->
            val result = findCombination(line)
            calibrationResults.add(result)
        }

        return calibrationResults.sum()
    }

    override fun part2(): Long {
        val calibrationResults = mutableListOf<Long>()
        data.forEach { line ->
            val result = findCombinationPart2(line)
            calibrationResults.add(result)
        }

        return calibrationResults.sum()
    }

    fun findCombinationPart2(input: String): Long {
        val (targetStr, numbersStr) = input.split(": ")
        val target = targetStr.toLong()
        val numbers = numbersStr.split(" ").map { it.toLong() }

        val operators = listOf("+", "*", "||")
        val operatorCombinations = generateOperatorCombinations(operators, numbers.size - 1)

        for (ops in operatorCombinations) {
            val result = evaluateExpression(numbers, ops)
            if (result == target) {
                return target
            }
        }

        return 0
    }

    private fun findCombination(input: String): Long {
        val (targetStr, numbersStr) = input.split(": ")
        val target = targetStr.toLong()
        val numbers = numbersStr.split(" ").map { it.toLong() }

        val operators = listOf("+", "*")
        val operatorCombinations = generateOperatorCombinations(operators, numbers.size - 1)

        for (ops in operatorCombinations) {
            val result = evaluateExpression(numbers, ops)
            if (result == target) {
                return target
            }
        }

        return 0
    }

    private fun generateOperatorCombinations(operators: List<String>, count: Int): List<List<String>> {
        if (count == 0) return listOf(emptyList())
        val smallerCombos = generateOperatorCombinations(operators, count - 1)
        return smallerCombos.flatMap { combo -> operators.map { op -> combo + op } }
    }

    private fun evaluateExpression(numbers: List<Long>, operators: List<String>): Long {
        var result = numbers[0]
        for (i in operators.indices) {
            result = when (operators[i]) {
                "+" -> result + numbers[i + 1]
                "*" -> result * numbers[i + 1]
                "||" -> concatenateNumbers(result, numbers[i + 1])
                else -> throw IllegalArgumentException("Unsupported operator: ${operators[i]}")
            }
        }
        return result
    }

    private fun concatenateNumbers(left: Long, right: Long): Long {
        return "$left$right".toLong()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day07())
        }
    }
}