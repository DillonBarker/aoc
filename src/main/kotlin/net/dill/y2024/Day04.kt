package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

open class Day04: Day() {
    override val data by lazy { resourceLines(2024, 4) }

    override fun part1(): Int {
        var count = 0
        val grid: List<List<Char>> = data.map { it.toList() }

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { index, char ->
                if (y == 0) {
                    if (char == 'X') {
                        if (searchDown(grid, y, index)) count++

                        if (searchLeft(row, index)) count++
                        if (searchLeftDownDiag(grid, y, index)) count++

                        if (searchRight(row, index)) count++
                        if (searchRightDownDiag(grid, row, y, index)) count++
                    }
                } else if (y == grid.size - 1) {
                    if (char == 'X') {
                        if (searchUp(grid, y, index)) count++

                        if (searchLeft(row, index)) count++
                        if (searchLeftUpDiag(grid, y, index)) count++

                        if (searchRight(row, index)) count++
                        if (searchRightUpDiag(grid, row, y, index)) count++
                    }
                } else {
                    if (char == 'X') {
                        if (searchUp(grid, y, index)) count++
                        if (searchDown(grid, y, index)) count++

                        if (searchLeft(row, index)) count++
                        if (searchLeftUpDiag(grid, y, index)) count++
                        if (searchLeftDownDiag(grid, y, index)) count++

                        if (searchRight(row, index)) count++
                        if (searchRightUpDiag(grid, row, y, index)) count++
                        if (searchRightDownDiag(grid, row, y, index)) count++
                    }
                }
            }


        }

        return count
    }

    private fun searchUp(grid: List<List<Char>>, y: Int, index: Int): Boolean {
        if (y >= 3) {
            if (grid[y - 1][index] == 'M') {
                if (grid[y - 2][index] == 'A') {
                    if (grid[y - 3][index] == 'S') {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun searchDown(grid: List<List<Char>>, y: Int, index: Int): Boolean {
        if (y <= grid.size - 4) {
            if (grid[y + 1][index] == 'M') {
                if (grid[y + 2][index] == 'A') {
                    if (grid[y + 3][index] == 'S') {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun searchLeft(row: List<Char>, index: Int): Boolean {
        if (index >= 3) {
            if (row[index - 1] == 'M') {
                if (row[index - 2] == 'A') {
                    if (row[index - 3] == 'S') {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun searchLeftUpDiag(grid: List<List<Char>>, y: Int, index: Int): Boolean {
        if (index >= 3) {
            if (y >= 3) {
                if (grid[y - 1][index - 1] == 'M') {
                    if (grid[y - 2][index - 2] == 'A') {
                        if (grid[y - 3][index - 3] == 'S') {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun searchLeftDownDiag(grid: List<List<Char>>, y: Int, index: Int): Boolean {
        if (index >= 3) {
            if (y <= grid.size - 4) {
                if (grid[y + 1][index - 1] == 'M') {
                    if (grid[y + 2][index - 2] == 'A') {
                        if (grid[y + 3][index - 3] == 'S') {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun searchRight(row: List<Char>, index: Int): Boolean {
        if (index <= row.size - 4) {
            if (row[index + 1] == 'M') {
                if (row[index + 2] == 'A') {
                    if (row[index + 3] == 'S') {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun searchRightUpDiag(grid: List<List<Char>>, row: List<Char>, y: Int, index: Int): Boolean {
        if (index <= row.size - 4) {
            if (y >= 3) {
                if (grid[y - 1][index + 1] == 'M') {
                    if (grid[y - 2][index + 2] == 'A') {
                        if (grid[y - 3][index + 3] == 'S') {
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

    private fun searchRightDownDiag(grid: List<List<Char>>, row: List<Char>, y: Int, index: Int): Boolean {
        if (index <= row.size - 4) {
            if (y <= grid.size - 4) {
                if (grid[y + 1][index + 1] == 'M') {
                    if (grid[y + 2][index + 2] == 'A') {
                        if (grid[y + 3][index + 3] == 'S') {
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

    override fun part2(): Int {
        var count = 0

        val grid: List<List<Char>> = data.map { it.toList() }

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { index, char ->
                var firstXmas = false
                var secondXmas = false

                if (y != 0 && y != grid.size - 1 && index != 0 && index != row.size - 1) {
                    if (char == 'A') {
                        // LOOK AT TOP LEFT AND BOTTOM RIGHT
                        if (grid[y - 1][index - 1] == 'M') {
                            if (grid[y + 1][index + 1] == 'S') {
                                firstXmas = true
                            }
                        } else if (grid[y - 1][index - 1] == 'S') {
                            if (grid[y + 1][index + 1] == 'M') {
                                firstXmas = true
                            }
                        }
                        // LOOK AT TOP RIGHT TO BOTTOM LEFT
                        if (grid[y - 1][index + 1] == 'M') {
                            if (grid[y + 1][index - 1] == 'S') {
                                secondXmas = true
                            }
                        } else if (grid[y - 1][index + 1] == 'S') {
                            if (grid[y + 1][index - 1] == 'M') {
                                secondXmas = true
                            }
                        }
                    }
                }

                if (firstXmas && secondXmas) {
                    count++
                }
            }


        }

        return count
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day04())
        }
    }
}