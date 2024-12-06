package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day06: Day() {
    override val data by lazy { resourceLines(2024, 6) }

    override fun part1(): Int {
        val visitedLocations = mutableSetOf<Pair<Int, Int>>()

        val grid: List<List<Char>> = data.map { it.toList() }

        var guardPosition = run {
            grid.forEachIndexed { y, row ->
                row.forEachIndexed { x, char ->
                    if (char == '^') return@run Pair(x, y)
                }
            }
            null
        }!!

        val directions = listOf('^', '>', 'v', '<')
        var currentDirectionIndex = 0

        visitedLocations.add(guardPosition)

        var guardActive = true

        while (guardActive) {
            val (x, y) = guardPosition
            val currentDirection = directions[currentDirectionIndex]

            val nextPosition = when (currentDirection) {
                '^' -> Pair(x, y - 1)
                '>' -> Pair(x + 1, y)
                'v' -> Pair(x, y + 1)
                '<' -> Pair(x - 1, y)
                else -> error("Invalid direction")
            }

            val (nextX, nextY) = nextPosition
            if (nextY !in grid.indices || nextX !in grid[nextY].indices) {
                guardActive = false
            } else {
                val nextChar = grid[nextY][nextX]

                if (nextChar == '#') {
                    currentDirectionIndex = (currentDirectionIndex + 1) % directions.size
                } else {
                    guardPosition = nextPosition

                    visitedLocations.add(guardPosition)
                }
            }
        }

        return visitedLocations.size
    }

    override fun part2(): Int {
        val visitedLocations = mutableSetOf<Pair<Pair<Int, Int>, Int>>()
        val grid: List<List<Char>> = data.map { it.toList() }

        val guardPosition = run {
            grid.forEachIndexed { y, row ->
                row.forEachIndexed { x, char ->
                    if (char == '^') return@run Pair(x, y)
                }
            }
            null
        }!!

        val directions = listOf(Pair(0, -1), Pair(1, 0), Pair(0, 1), Pair(-1, 0))
        val currentDirectionIndex = 0

        visitedLocations.add(Pair(guardPosition, currentDirectionIndex))

        var loopCount = 0

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == '^' || grid[y][x] == '#') continue

                val modifiedGrid = grid.map { it.toMutableList() }
                modifiedGrid[y][x] = '#'

                val visitedDuringLoop = mutableSetOf<Pair<Pair<Int, Int>, Int>>()
                var currentPos = guardPosition
                var currentDirection = currentDirectionIndex

                visitedDuringLoop.add(Pair(currentPos, currentDirection))

                val isInGrid = true

                while (isInGrid) {
                    val (currentX, currentY) = currentPos
                    val currentDirectionPair = directions[currentDirection]

                    val nextPosition = Pair(currentX + currentDirectionPair.first, currentY + currentDirectionPair.second)

                    if (nextPosition.second !in modifiedGrid.indices || nextPosition.first !in modifiedGrid[nextPosition.second].indices) {
                        break
                    }

                    val nextChar = modifiedGrid[nextPosition.second][nextPosition.first]

                    if (nextChar == '#') {
                        currentDirection = (currentDirection + 1) % 4
                    } else {
                        currentPos = nextPosition
                    }

                    if (!visitedDuringLoop.add(Pair(currentPos, currentDirection))) {
                        loopCount++
                        break
                    }
                }
            }
        }

        return loopCount
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day06())
        }
    }
}