package net.dill.y2025

import net.dill.Day
import net.dill.resourceLines

open class Day02: Day() {
    override val data by lazy { resourceLines(2025, 2) }

    override fun part1(): Long {
        val ranges = data[0].split(",")
        var invalidIds = 0L

        ranges.forEach { range ->
            val (start, end) = range.split("-").map { it.toLong() }
            for (i in start..end) {
                if (isInvalid(i)) {
                    invalidIds += i
                }
            }
        }


        return invalidIds
    }

    private fun isInvalid(id: Long): Boolean {
        val stringedId = id.toString()

        if (stringedId.length % 2 != 0) return false
        val mid = stringedId.length / 2

        val first = stringedId.take(mid)
        val second = stringedId.substring(mid)

        return first == second
    }

    override fun part2(): Long {
        val ranges = data[0].split(",")
        var invalidIds = 0L

        ranges.forEach { range ->
            val (start, end) = range.split("-").map { it.toLong() }
            for (i in start..end) {
                if (isInvalid(i)) {
                    invalidIds += i
                }
                else if (isInvalidPart2(i)) {
                    invalidIds += i
                }
            }
        }

        return invalidIds
    }

    private fun isInvalidPart2(id: Long): Boolean {
        val stringedId = id.toString()
        return hasRepeatedPattern(stringedId)
    }

    fun hasRepeatedPattern(string: String, minParts: Int = 3): Boolean {
        val length = string.length

        // Try all possible n from minParts up to the string length
        for (n in minParts..length) {
            if (length % n != 0) continue  // can only split evenly

            val partSize = length / n
            val first = string.take(partSize)

            val allMatch = (1 until n).all { i ->
                string.substring(i * partSize, (i + 1) * partSize) == first
            }

            if (allMatch) return true
        }

        return false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day02())
        }
    }
}
