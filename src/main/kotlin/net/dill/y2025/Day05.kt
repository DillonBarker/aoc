package net.dill.y2025

import net.dill.Day
import net.dill.resourceLines

open class Day05: Day() {
    override val data by lazy { resourceLines(2025, 5) }

    override fun part1(): Long {
        val ranges = mutableListOf<Pair<Long, Long>>()
        val idsToSearch = mutableListOf<Long>()
        var freshCount = 0L

        data.forEach { line ->
            if (line.contains("-")) {
                val range = line.split("-").map { it.toLong() }
                ranges.add(Pair(range[0], range[1]))
            } else if (line.isNotBlank()) {
                idsToSearch.add(line.toLong())
            }
        }

        idsToSearch.forEach { id ->
            if (ranges.any { (start, end) -> id in start..end }) freshCount++
        }

        return freshCount
    }

    override fun part2(): Long {
        val ranges = mutableListOf<Pair<Long, Long>>()

        data.forEach { line ->
            if (line.contains("-")) {
                val range = line.split("-").map { it.toLong() }
                ranges.add(Pair(range[0], range[1]))
            }
        }

        val sortedRanges = ranges.sortedBy { it.first }
        val merged = mutableListOf<Pair<Long, Long>>()

        sortedRanges.forEach { (start, end) ->
            if (merged.isEmpty() || merged.last().second < start - 1) {
                merged.add(Pair(start, end))
            } else {
                val last = merged.removeLast()
                merged.add(Pair(last.first, maxOf(last.second, end)))
            }
        }

        return merged.sumOf { (start, end) -> end - start + 1 }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day05())
        }
    }
}
