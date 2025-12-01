package net.dill.y2025

import net.dill.Day
import net.dill.resourceLines

open class Day01: Day() {
    override val data by lazy { resourceLines(2025, 1) }

    override fun part1(): Int {
        var pointer = 50
        var password = 0
        data.forEach { line ->
            val direction = line.first().toString()
            val amount = line.drop(1).toInt()
            pointer = when (direction) {
                "R" -> ((pointer + amount) % 100 + 100) % 100
                "L" -> ((pointer - amount) % 100 + 100) % 100
                else -> pointer
            }
            if (pointer == 0) {
                password++
            }
        }
        return password
    }

    override fun part2(): Int {
        var pointer = 50
        var count = 0

        data.forEach { line ->
            val direction = line.first()
            val amount = line.drop(1).toInt()

            when (direction) {
                'R' -> {
                    val rangeStart = pointer + 1
                    val rangeEnd = pointer + amount
                    count += rangeEnd / 100 - (rangeStart - 1) / 100
                    pointer = (pointer + amount) % 100
                }
                'L' -> {
                    if (pointer == 0) {
                        count += amount / 100
                    } else if (amount >= pointer) {
                        val afterCrossingZero = amount - pointer
                        count += 1 + (afterCrossingZero / 100)
                    }
                    pointer = ((pointer - amount) % 100 + 100) % 100
                }
            }
        }

        return count
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day01())
        }
    }
}
