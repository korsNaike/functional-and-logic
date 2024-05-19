package LR6

import kotlin.math.abs
import java.lang.System.`in`
import java.util.*

val scanner = Scanner(`in`)

fun vvodd(n: Int, m: MutableList<Int>) {
    if (n>0) {
        m.add(scanner.nextInt())
        vvodd(n-1, m)
    }
}
fun vvod(n: Int):MutableList<Int> {
    val m:MutableList<Int> = mutableListOf()
    vvodd(n, m)
    return m
}

tailrec fun arrayOP(m: MutableList<Int>,i: Int, a: Int = 0, f: (Int, Int) -> Int): Int =
    if (i <= 0) a else arrayOP(m,i-1, f(a, m[i-1]), f)

fun sumd(m: MutableList<Int>): Int = arrayOP(m, m.size, 0) { a, b -> (a + b) }
fun muld(m: MutableList<Int>): Int = arrayOP(m, m.size, 1) { a, b -> (a * b) }
fun maxd(m: MutableList<Int>): Int = arrayOP(m, m.size, m[0]) { a, b -> if (a > b) a else b }
fun mind(m: MutableList<Int>): Int = arrayOP(m, m.size, m[0]) { a, b -> if (a < b) a else b }

fun freq(m: MutableList<Int>): Int
{
    if (m.isNotEmpty()) {
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (x in m) map[x] = map.getOrDefault(x, 0) + 1
        var k = 1
        var e: Int = map.values.first()
        for (x in map) if (x.value > k) {
            k = x.value
            e = x.key
        }
        return e
    } else return 0
}

fun new2freq(m: MutableList<Int>): MutableList<Int>
{
    if (m.isNotEmpty()) {
        val n:MutableList<Int> = mutableListOf()
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (x in m) map[x] = map.getOrDefault(x, 0) + 1
        for (x in map) if ((x.key % 2 == 0) and (x.value % 2 == 0)) n.add(x.key)
        return n
    } else return mutableListOf()
}

//сумма цифр вверх
fun sumc(n: Int): Int = if (n < 10) n else (n % 10) + sumc(n / 10)

fun newlist(m: MutableList<Int>): MutableList<Int>
{
    if (m.isNotEmpty()) {
        val n:MutableList<Int> = mutableListOf()
        for (x in m) if ((x < 0) and (sumc(abs(x)) < 10)) n.add(x)
        return n
    } else return mutableListOf()
}

fun main() {
    println("Hello World!")
    val scanner = Scanner(`in`)
    val n: Int = scanner.nextInt()
    val m = vvod(n)
    println(m)
    println(sumd(m))
    println(muld(m))
    println(maxd(m))
    println(mind(m))
    println(freq(m))
}