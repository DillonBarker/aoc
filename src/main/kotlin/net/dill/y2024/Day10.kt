package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day10: Day() {
    override val data by lazy { resourceLines(2024, 10) }

    override fun part1(): Int {
        var score = 0
        val grid: List<List<Char>> = data.map { it.toList() }
        val trailHeads = mutableListOf<Pair<Int, Int>>()

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                if (char == '0') trailHeads.add(Pair(x, y))
            }
        }

        trailHeads.forEach { trailHead ->
            val path1s = findPaths(grid, listOf(trailHead), '1')
            val path2s = findPaths(grid, path1s, '2')
            val path3s = findPaths(grid, path2s, '3')
            val path4s = findPaths(grid, path3s, '4')
            val path5s = findPaths(grid, path4s, '5')
            val path6s = findPaths(grid, path5s, '6')
            val path7s = findPaths(grid, path6s, '7')
            val path8s = findPaths(grid, path7s, '8')
            val path9s = findPaths(grid, path8s, '9')
            score += path9s.toSet().size
        }

        return score
    }

    private fun findPaths(grid: List<List<Char>>, starts: List<Pair<Int, Int>>, char: Char): List<Pair<Int, Int>> {
        val paths = mutableListOf<Pair<Int, Int>>()

        starts.forEach { (x, y) ->
            if (y != 0) {
                if (grid[y-1][x] == char) paths.add(Pair(x, y-1))
            }
            if (x != 0) {
                if (grid[y][x-1] == char) paths.add(Pair(x-1, y))
            }
            if (x != grid[0].size - 1) {
                if (grid[y][x+1] == char) paths.add(Pair(x+1, y))
            }
            if (y != grid.size - 1) {
                if (grid[y+1][x] == char) paths.add(Pair(x, y+1))
            }
        }

        return paths
    }

    override fun part2(): Int {
        var sumOfRating = 0
        val grid: List<List<Char>> = data.map { it.toList() }
        val trailHeads = mutableListOf<Pair<Int, Int>>()

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                if (char == '0') trailHeads.add(Pair(x, y))
            }
        }

        trailHeads.forEach { trailHead ->
            val path1s = findPaths(grid, listOf(trailHead), '1')
            val path2s = findPaths(grid, path1s, '2')
            val path3s = findPaths(grid, path2s, '3')
            val path4s = findPaths(grid, path3s, '4')
            val path5s = findPaths(grid, path4s, '5')
            val path6s = findPaths(grid, path5s, '6')
            val path7s = findPaths(grid, path6s, '7')
            val path8s = findPaths(grid, path7s, '8')
            val path9s = findPaths(grid, path8s, '9')
            sumOfRating += path9s.size
        }

        return sumOfRating
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day10())
        }
    }
}