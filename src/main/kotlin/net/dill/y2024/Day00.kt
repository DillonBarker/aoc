package net.dill.y2024

import net.dill.resourceLines

class Day00 {
    private val data by lazy { resourceLines(2024, 0) }

    fun part1(): Int {
        var counter = 0

        data.forEach {
            counter += it.toInt()
        }

        println(counter)
        return counter
    }

    fun part2(): Int? {
        var counter = 0
        val map = mutableMapOf<Int, Int>()
        var found: Int? = null

        while (found == null) {
            val dataset = data
            dataset.forEach {
                if (found == null) {
                    counter += it.toInt()
                    map[counter] = 1 + (map[counter] ?: 0)
                    found = map.values.find { it == 2 }
                    if (found != null) {
                        for ((key, value) in map) {
                            if (value == 2) {
                                found = key
                            }
                        }
                    }
                }
            }
        }

        println(found)
        return found
    }

    fun solve() {
        part1()
        part2()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val day = Day00()
            day.solve()
        }
    }
}