package net.dill

import java.io.InputStream
import java.util.stream.Collectors

internal object Resources

fun resource(year: Int, day: Int, test: String?): InputStream {
    val name = String.format("/%d/day%02d%s.txt", year, day, test)
    return resourceStream(name)!!
}

fun resourceStream(name: String): InputStream? {
    return Resources.javaClass.getResourceAsStream(name)
}

fun resourceLines(year: Int, day: Int, test: String = ""): List<String> {
    return resource(year, day, test).bufferedReader().lines().collect(Collectors.toList())
}
