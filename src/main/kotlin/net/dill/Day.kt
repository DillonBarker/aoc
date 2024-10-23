package net.dill

abstract class Day() {
    abstract fun part1(): Any?
    abstract fun part2(): Any?

    open fun solve() {
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
    }

    companion object {
        @JvmStatic
        fun run(dayInstance: Day) {
            dayInstance.solve()
        }
    }
}