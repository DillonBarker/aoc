package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day03: Day() {
    override val data by lazy { resourceLines(2024, 3) }

    override fun part1(): Int {
        val input = data[0]
        val regex = """mul\(\d+,\d+\)""".toRegex()
        val matches = regex.findAll(input).map { it.value }.toList()
        val products = mutableListOf<Int>()

        matches.map { match ->
            val numberPart = match.substringAfter("(").substringBefore(")")
            val firstNum = numberPart.split(",")[0].toInt()
            val secondNum = numberPart.split(",")[1].toInt()
            products.add(firstNum * secondNum)
        }

        return products.sum()
    }

    override fun part2(): Int {
        val input = data[0]

        val inputs = input.split("don't()")
        val products = mutableListOf<Int>()

        inputs.forEachIndexed { index, s ->
            if (index == 0) {
                products.addAll(findMatches(s))
            }else {
                val stringSplitByDo = s.split("do()")
                if (stringSplitByDo.size > 1) {
                    stringSplitByDo.drop(1).forEach { segment ->
                        products.addAll(findMatches(segment))
                    }
                }
            }
        }

        return products.sum()
    }

    private fun findMatches(string: String): List<Int> {
        val regex = """mul\(\d+,\d+\)""".toRegex()
        val matches = regex.findAll(string).map { it.value }.toList()
        val products = mutableListOf<Int>()

        matches.map { match ->
            val numberPart = match.substringAfter("(").substringBefore(")")
            val firstNum = numberPart.split(",")[0].toInt()
            val secondNum = numberPart.split(",")[1].toInt()
            products.add(firstNum * secondNum)
        }

        return products
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day03())
        }
    }
}