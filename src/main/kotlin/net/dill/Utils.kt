package net.dill

import java.io.InputStream
import java.util.stream.Collectors

internal object Resources

fun resource(year: Int, day: Int): InputStream {
    val name = String.format("/%d/day%02d.txt", year, day)
    return resourceStream(name)!!
}

fun resourceStream(name: String): InputStream? {
    return Resources.javaClass.getResourceAsStream(name)
}

fun resourceLines(year: Int, day: Int): List<String> {
    return resource(year, day).bufferedReader().lines().collect(Collectors.toList())
}
