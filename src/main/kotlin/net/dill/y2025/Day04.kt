package net.dill.y2025

import net.dill.Day
import net.dill.resourceLines

open class Day04: Day() {
    override val data by lazy { resourceLines(2025, 4) }

    override fun part1(): Int {
        var rolls = 0
        val grid: List<List<Char>> = data.map { it.toList() }

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                if (char == '@') {
                    var adjacentCount = 0
                    for (dy in -1..1) {
                        for (dx in -1..1) {
                            if (dy == 0 && dx == 0) continue
                            val newY = y + dy
                            val newX = x + dx
                            if (newY in grid.indices && newX in row.indices) {
                                if (grid[newY][newX] == '@') {
                                    adjacentCount++
                                }
                            }
                        }
                    }
                    if (adjacentCount <= 3) {
                        rolls++
                    }
                }
            }
        }

        return rolls
    }

    override fun part2(): Int {
        var rolls = 0
        val grid: MutableList<MutableList<Char>> = data.map { it.toMutableList() } as MutableList<MutableList<Char>>

        var totalRolls = 0
        var canRoll = true
        while (canRoll) {
            var rollsThisIteration = 0
            grid.forEachIndexed { y, row ->
                row.forEachIndexed { x, char ->
                    if (char == '@') {
                        var adjacentCount = 0
                        for (dy in -1..1) {
                            for (dx in -1..1) {
                                if (dy == 0 && dx == 0) continue
                                val newY = y + dy
                                val newX = x + dx
                                if (newY in grid.indices && newX in row.indices) {
                                    if (grid[newY][newX] == '@') {
                                        adjacentCount++
                                    }
                                }
                            }
                        }
                        if (adjacentCount <= 3) {
                            rollsThisIteration++
                            grid[y][x] = '.'
                        }
                    }
                }
            }
            if (rollsThisIteration == 0) {
                canRoll = false
            }
            totalRolls += rollsThisIteration
        }
        rolls = totalRolls

        return rolls
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day04())
        }
    }
}
