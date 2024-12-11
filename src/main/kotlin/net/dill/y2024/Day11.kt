package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day11: Day() {
    override val data by lazy { resourceLines(2024, 11) }

    override fun part1(): Int {
        val stones = data[0]
        val blinks = 25

        var splitStones = stones.split(" ").toMutableList().map { it.toLong() }

        repeat(blinks) {
            val newStones = mutableListOf<Long>()
            splitStones.forEach { stone ->
                if (stone == 0L) {
                    newStones.add(1L)
                } else if (stone.toString().length % 2 == 0) {
                    val middle = stone.toString().length / 2
                    val firstHalf = stone.toString().substring(0, middle).toLong()
                    val secondHalf = stone.toString().substring(middle).toLong()
                    newStones.add(firstHalf)
                    newStones.add(secondHalf)
                } else {
                    newStones.add(stone * 2024)
                }
            }
            splitStones = newStones
        }

        return splitStones.size
    }

    override fun part2(): Long {
        val stoneCounts = mutableMapOf<Long, Long>()
        data[0].split(" ").map { it.toLong() }.forEach {
            stoneCounts[it] = stoneCounts.getOrDefault(it, 0) + 1
        }
        val blinks = 75

        repeat(blinks) {
            val newStoneCounts = mutableMapOf<Long, Long>()
            for ((stone, count) in stoneCounts) {
                when {
                    stone == 0L -> {
                        newStoneCounts[1L] = newStoneCounts.getOrDefault(1L, 0) + count
                    }
                    stone.toString().length % 2 == 0 -> {
                        val middle = stone.toString().length / 2
                        val firstHalf = stone.toString().substring(0, middle).toLong()
                        val secondHalf = stone.toString().substring(middle).toLong()

                        newStoneCounts[firstHalf] = newStoneCounts.getOrDefault(firstHalf, 0) + count
                        newStoneCounts[secondHalf] = newStoneCounts.getOrDefault(secondHalf, 0) + count
                    }
                    else -> {
                        newStoneCounts[stone * 2024] = newStoneCounts.getOrDefault(stone * 2024, 0) + count
                    }
                }
            }

            stoneCounts.clear()
            stoneCounts.putAll(newStoneCounts)
        }

        return stoneCounts.values.sum()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day11())
        }
    }
}