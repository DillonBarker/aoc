package net.dill.y2018

import net.dill.Day
import net.dill.resourceLines

class Day01 : Day() {
    override val data by lazy { resourceLines(2018, 1) }

    override fun part1(): Int {
        var counter = 0

        data.forEach {
            counter += it.toInt()
        }

        return counter
    }

    override fun part2(): Int? {
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

        return found
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day01())
        }
    }
}