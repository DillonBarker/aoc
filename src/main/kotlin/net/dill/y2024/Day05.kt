package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day05: Day() {
    override val data by lazy { resourceLines(2024, 5) }

    override fun part1(): Int {
        var total = 0
        val blankLineIndex = data.indexOf("")
        val orderingRules = data.subList(0, blankLineIndex).map {
            val split = it.split("|")
            Pair(split[0].toInt(), split[1].toInt())
        }
        val updates = data.subList(blankLineIndex + 1, data.size)

        updates.forEach {
            val update = it.split(",").map { it.toInt() }
            var valid = false
            update.forEachIndexed { index, pageNumber ->
                if (index == 0) {
                    valid = validFirstPageNumber(orderingRules, update, pageNumber)
                } else {
                    if (valid) {
                        val pagesToCheckBefore = update.subList(0, index)
                        valid = checkBefore(orderingRules, pagesToCheckBefore, pageNumber)

                        val pagesToCheckAfter = update.subList(index + 1, update.size)
                        valid = checkAfter(orderingRules, pagesToCheckAfter, pageNumber)

                    }
                }
            }
            if (valid) {
               total += getMiddleValue(update)
            }
        }

        return total
    }

    override fun part2(): Int {
        var total = 0
        val blankLineIndex = data.indexOf("")
        val orderingRules = data.subList(0, blankLineIndex).map {
            val split = it.split("|")
            Pair(split[0].toInt(), split[1].toInt())
        }
        val updates = data.subList(blankLineIndex + 1, data.size)
        val incorrectlyOrderedUpdates = mutableListOf<List<Int>>()

        updates.forEach {
            val update = it.split(",").map { it.toInt() }
            var valid = false
            update.forEachIndexed { index, pageNumber ->
                if (index == 0) {
                    valid = validFirstPageNumber(orderingRules, update, pageNumber)
                } else {
                    if (valid) {
                        val pagesToCheckBefore = update.subList(0, index)
                        valid = checkBefore(orderingRules, pagesToCheckBefore, pageNumber)

                        val pagesToCheckAfter = update.subList(index + 1, update.size)
                        valid = checkAfter(orderingRules, pagesToCheckAfter, pageNumber)

                    }
                }
            }
            if (!valid) {
                incorrectlyOrderedUpdates.add(update)
            }
        }

        incorrectlyOrderedUpdates.forEach { update ->
            val reorderedList = reorderList(update, orderingRules)
            total += getMiddleValue(reorderedList)
        }

        return total
    }

    private fun reorderList(input: List<Int>, orderingRules: List<Pair<Int, Int>>): List<Int> {
        val dependencies = mutableMapOf<Int, MutableSet<Int>>()
        input.forEach { num -> dependencies[num] = mutableSetOf() }

        orderingRules.forEach { (first, second) ->
            if (first in input && second in input) {
                dependencies[first]?.add(second)
            }
        }

        val result = mutableListOf<Int>()
        val remaining = input.toMutableList()

        while (remaining.isNotEmpty()) {
            val candidate = remaining.firstOrNull { num ->
                dependencies[num]?.none { it in remaining } ?: true
            }
            if (candidate != null) {
                result.add(candidate)
                remaining.remove(candidate)
            } else {
                val first = remaining.first()
                result.add(first)
                remaining.removeAt(0)
            }
        }

        return result
    }


    private fun getMiddleValue(list: List<Int>): Int {
        val length = list.size
        return if (length == 0) {
            0
        } else if (length % 2 == 0) {
            list[length / 2 - 1]
        } else {
            list[length / 2]
        }
    }

    private fun checkAfter(orderingRules: List<Pair<Int, Int>>, pagesToCheck: List<Int>, targetPageNumber: Int): Boolean {
        return pagesToCheck.all { page ->
            orderingRules.any { rule ->
                rule.first == targetPageNumber && rule.second == page
            }
        }
    }

    private fun checkBefore(orderingRules: List<Pair<Int, Int>>, pagesToCheck: List<Int>, targetPageNumber: Int): Boolean {
        return pagesToCheck.all { page ->
            orderingRules.any { rule ->
                rule.first == page && rule.second == targetPageNumber
            }
        }
    }

    private fun validFirstPageNumber(orderingRules: List<Pair<Int,Int>>, validPageNumbers: List<Int>, targetPageNumber: Int): Boolean {
        return orderingRules.all { pair ->
            if (validPageNumbers.contains(pair.first)) {
                pair.second != targetPageNumber
            } else {
                true
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day05())
        }
    }
}