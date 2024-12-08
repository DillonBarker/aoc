package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day08: Day() {
    override val data by lazy { resourceLines(2024, 8) }

    override fun part1(): Int {
        val rows = data.size
        val cols = data[0].length

        val antennasByChar = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        val validPositions = mutableSetOf<Pair<Int, Int>>()


        for (i in data.indices) {
            for (j in data[i].indices) {
                val char = data[i][j]
                if (char in ('a'..'z') || char in ('A'..'Z') || char in ('0'..'9')) {
                    antennasByChar.computeIfAbsent(char) { mutableListOf() }.add(i to j)
                }
            }
        }


        for ((_, antennas) in antennasByChar) {
            if (antennas.size < 2) continue
            for (i in antennas.indices) {
                for (j in antennas.indices) {
                    if (i != j) {
                        val (x1, y1) = antennas[i]
                        val (x2, y2) = antennas[j]

                        val dx = x2 - x1
                        val dy = y2 - y1

                        val antinode1 = x1 - dx to y1 - dy
                        val antinode2 = x2 + dx to y2 + dy

                        if (antinode1.first in 0 until rows && antinode1.second in 0 until cols) {
                            validPositions.add(antinode1)
                        }
                        if (antinode2.first in 0 until rows && antinode2.second in 0 until cols) {
                            validPositions.add(antinode2)
                        }
                    }
                }
            }
        }

        return validPositions.size
    }

    override fun part2(): Int {
        val rows = data.size
        val cols = data[0].length

        val antennasByChar = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        val validPositions = mutableSetOf<Pair<Int, Int>>()


        for (i in data.indices) {
            for (j in data[i].indices) {
                val char = data[i][j]
                if (char in ('a'..'z') || char in ('A'..'Z') || char in ('0'..'9')) {
                    antennasByChar.computeIfAbsent(char) { mutableListOf() }.add(i to j)
                }
            }
        }


        for ((_, antennas) in antennasByChar) {
            if (antennas.size < 2) continue
            antennas.forEach {
                validPositions.add(it)
            }
            for (i in antennas.indices) {
                for (j in antennas.indices) {
                    if (i != j) {
                        val (x1, y1) = antennas[i]
                        val (x2, y2) = antennas[j]

                        val dx = x2 - x1
                        val dy = y2 - y1

                        val antinodeStart1 = x1 - dx to y1 - dy
                        val antinodeStart2 = x2 + dx to y2 + dy

                        if (antinodeStart1.first in 0 until rows && antinodeStart1.second in 0 until cols) {
                            var currentAntinode = antinodeStart1

                            while (currentAntinode.first in 0 until rows && currentAntinode.second in 0 until cols) {
                                validPositions.add(currentAntinode)
                                currentAntinode = currentAntinode.first - dx to currentAntinode.second - dy
                            }
                        }
                        if (antinodeStart2.first in 0 until rows && antinodeStart2.second in 0 until cols) {
                            var currentAntinode = antinodeStart2

                            while (currentAntinode.first in 0 until rows && currentAntinode.second in 0 until cols) {
                                validPositions.add(currentAntinode)
                                currentAntinode = currentAntinode.first + dx to currentAntinode.second + dy
                            }

                        }
                    }
                }
            }
        }

        return validPositions.size
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day08())
        }
    }
}