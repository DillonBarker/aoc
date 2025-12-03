package net.dill.y2025

import net.dill.Day
import net.dill.resourceLines

open class Day03: Day() {
    override val data by lazy { resourceLines(2025, 3) }

    override fun part1(): Int {
        var totalVoltage = 0

        data.forEach { line ->
            val bank = line.split("").drop(1).dropLast(1).map { it.toInt() }
            val highestVoltage = maxTwoDigitVoltage(bank)

            totalVoltage += highestVoltage
        }

        return totalVoltage
    }

    fun maxTwoDigitVoltage(bank: List<Int>): Int {
        var best = -1

        for (i in bank.indices) {
            for (j in i + 1 until bank.size) {
                val value = bank[i] * 10 + bank[j]
                if (value > best) best = value
            }
        }

        return best
    }

    override fun part2(): Long {
        var totalVoltage = 0L

        data.forEach { line ->
            val bank = line.split("").drop(1).dropLast(1).map { it.toInt() }
            val highestVoltage = maxTwelveDigitVoltage(bank)

            totalVoltage += highestVoltage
        }

        return totalVoltage
    }

    fun maxTwelveDigitVoltage(bank: List<Int>): Long {
        val result = mutableListOf<Int>()
        var start = 0

        while (result.size < 12) {
            val remaining = 12 - result.size
            val mustKeep = bank.size - start - remaining

            var maxIdx = start
            for (i in start..start + mustKeep) {
                if (bank[i] > bank[maxIdx]) {
                    maxIdx = i
                }
            }

            result.add(bank[maxIdx])
            start = maxIdx + 1
        }

        return result.joinToString("").toLong()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day03())
        }
    }
}
