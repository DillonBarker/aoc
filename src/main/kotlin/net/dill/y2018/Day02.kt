package net.dill.y2018

import net.dill.Day
import net.dill.resourceLines

class Day02 : Day() {
    override val data by lazy { resourceLines(2018, 2) }

    override fun part1(): Int {
        var twice = 0
        var thrice = 0
        data.forEach {
            var appearsTwice = false
            var appearsThrice = false
            val map = mutableMapOf<String, Int>()
            val chars = it.split("").filter { it.isNotBlank() }
            chars.forEach { char ->
                map[char] = (map[char]?: 0) + 1
            }
            map.forEach { key, value ->
                if (value == 2) {
                    appearsTwice = true
                }
                if (value == 3) {
                    appearsThrice = true
                }
            }
            if (appearsTwice) {
                twice++
            }
            if (appearsThrice) {
                thrice++
            }
        }

        return twice * thrice
    }

    override fun part2(): String {
        val len = data.size
        var answer = ""

        for (i in 0 until len) {
            if (answer.isEmpty())
                data.forEach {
                    val found = stringHasOneDifference(data[i], it)
                    if (found.first != null) {
                        for (each in 0 until (found.first!!.length - 1)) {
                            if (found.first!![each] != found.second!![each]) {
                                answer = found.first!!.removeRange(each, each+1)
                            }
                        }
                    }
                }
        }

        return answer
    }

    fun stringHasOneDifference(string1: String, string2: String): Pair<String?, String?> {
        if (string1.length != string2.length) return Pair(null, null)
        if (string1 == string2) return Pair(null, null)
        val length = string1.length
        var similarCount = 0

        for (i in 0 until length) {
            if (string1[i] == string2[i]) {
                similarCount++
            }
        }

        if (similarCount == length - 1) {
            return Pair(string1, string2)
        }

        return Pair(null, null)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day02())
        }
    }
}