package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

class Day01 : Day() {
    override val data by lazy { resourceLines(2024, 1) }

    override fun part1(): Int {
        val firstNums: MutableList<Int> = mutableListOf()
        val secondNums: MutableList<Int> = mutableListOf()
        val distances: MutableList<Int> = mutableListOf()
        data.forEach {
            val nums = it.split("   ")
            firstNums.add(nums[0].toInt())
            secondNums.add(nums[1].toInt())
        }
        firstNums.sort()
        secondNums.sort()

        firstNums.forEachIndexed { index, firstNum ->
            val secondNum = secondNums[index]
            if (secondNum >= firstNum) {
                distances.add(secondNum-firstNum)
            } else {
                distances.add(firstNum-secondNum)
            }
        }
        return distances.sum()
    }

    override fun part2(): Int {
        val firstNums: MutableList<Int> = mutableListOf()
        val secondNums: MutableList<Int> = mutableListOf()
        val occurences: MutableList<Int> = mutableListOf()
        data.forEach {
            val nums = it.split("   ")
            firstNums.add(nums[0].toInt())
            secondNums.add(nums[1].toInt())
        }
        firstNums.forEach { firstNum ->
            var count = 0
            secondNums.forEach { secondNum ->
                if (firstNum == secondNum) {
                    count++
                }
            }
            occurences.add(firstNum * count)
        }

        return occurences.sum()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day01())
        }
    }
}