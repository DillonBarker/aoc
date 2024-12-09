package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines
import kotlin.math.abs

open class Day09: Day() {
    override val data by lazy { resourceLines(2024, 9) }

    override fun part1(): Long {
        val diskMap = data[0]

        val diskMapWithFreeDiskSpace = mutableListOf<String>()
        var count = 0

        diskMap.forEachIndexed { index, char ->
            val fileId = count.toString()
            val charValue = char.digitToInt()
            if (index % 2 == 0) {
                count++
                repeat(charValue) { diskMapWithFreeDiskSpace.add(fileId) }
            } else {
                repeat (char.digitToInt()) { diskMapWithFreeDiskSpace.add(".") }
            }
        }

        while (diskMapWithFreeDiskSpace.contains(".")) {
            val removed = diskMapWithFreeDiskSpace.removeAt(diskMapWithFreeDiskSpace.size - 1)
            val index = diskMapWithFreeDiskSpace.indexOf(".")
            diskMapWithFreeDiskSpace[index] = removed
        }

        var fileSystemChecksum = 0L
        diskMapWithFreeDiskSpace.forEachIndexed { index, char ->
            fileSystemChecksum += (index * char.toLong())
        }

        return fileSystemChecksum
    }

    override fun part2(): Long {
        val diskMap = data[0]

        val diskMapWithFreeDiskSpace = mutableListOf<String>()
        var count = 0

        diskMap.forEachIndexed { index, char ->
            val fileId = count.toString()
            val charValue = char.digitToInt()
            if (index % 2 == 0) {
                count++
                repeat(charValue) { diskMapWithFreeDiskSpace.add(fileId) }
            } else {
                repeat(charValue) { diskMapWithFreeDiskSpace.add(".") }
            }
        }

        var str = groupConsecutive(diskMapWithFreeDiskSpace)
        val test = str.filter { !it.contains(".")}.reversed()

        test.forEach { s ->
            if (!s.contains(".")) {
                val sizeOfFile = s.size
                val modifyIndex = str.asReversed().indexOf(s)
                val indexOfSpace = str.reversed().indexOfLast { currentGroup ->
                    (currentGroup.contains(".") && currentGroup.size >= sizeOfFile)
                }
                if (indexOfSpace != -1 && indexOfSpace >= modifyIndex) {
                    val diff = abs(str.asReversed()[indexOfSpace].size - sizeOfFile)
                    val freeSpace = ".".repeat(diff).map { it.toString() }
                    str.asReversed()[modifyIndex] = ".".repeat(sizeOfFile).map { it.toString() }
                    str.asReversed()[indexOfSpace] = s
                    if (diff > 0) {
                        str.asReversed().add(indexOfSpace, freeSpace)
                    }
                }

            }
            str = consolidateDotGroups(str)
        }

        var fileSystemChecksum = 0L

        str.flatten().forEachIndexed { index, file ->
            if (file != ".") {
                fileSystemChecksum += file.toLong() * index.toLong()
            }
        }

        return fileSystemChecksum
    }

    private fun consolidateDotGroups(str: MutableList<List<String>>): MutableList<List<String>> {
        val consolidatedList = mutableListOf<List<String>>()

        for (group in str) {
            if (group.all { it == "." }) {
                if (consolidatedList.isEmpty() || !consolidatedList.last().all { it == "." }) {
                    consolidatedList.add(group)
                } else {
                    val lastGroup = consolidatedList.last().toMutableList()
                    lastGroup.addAll(group)
                    consolidatedList[consolidatedList.lastIndex] = lastGroup
                }
            } else {
                consolidatedList.add(group)
            }
        }

        return consolidatedList
    }

    private fun groupConsecutive(input: List<String>): MutableList<List<String>> {
        val result = mutableListOf<List<String>>()
        var currentGroup = mutableListOf<String>()

        for (i in input.indices) {
            if (currentGroup.isEmpty() || input[i] == currentGroup.last()) {
                currentGroup.add(input[i])
            } else {
                result.add(currentGroup)
                currentGroup = mutableListOf(input[i])
            }
        }

        if (currentGroup.isNotEmpty()) {
            result.add(currentGroup)
        }

        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day09())
        }
    }
}