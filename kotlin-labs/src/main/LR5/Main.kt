import java.lang.System.`in`
import java.util.*

class Main {
    //максимум из 3
    fun max3(x: Int, y: Int, z: Int): Int = if (x > y) if (x > z) x else z else if (y > z) y else z

    //факториал вверх
    fun factup(n: Int): Int = if (n <= 1) 1 else factup(n - 1) * n

    //факториал вниз
    tailrec fun factd(n: Int, a: Int): Int = if (n <= 1) n * a else factd(n - 1, n * a)
    fun factdown(n: Int): Int = factd(n, 1)

    //сумма цифр вниз
    fun sumcda(n: Int, a: Int): Int = if (n < 10) n + a else sumcda(n / 10, a + (n % 10))
    fun sumcd(n: Int): Int = sumcda(n, 0)

    //сумма цифр вверх
    fun sumc(n: Int): Int = if (n < 10) n else (n % 10) + sumc(n / 10)

    //произведение цифр вверх
    fun mulc(n: Int): Int = if (n < 10) n else (n % 10) * mulc(n / 10)

    //функция высшего порядка возвращает функцию
    fun calc(f: Boolean): (Int) -> Int = if (f) ::sumc else ::mulc

    //функция высшего порядка принимает функцию
    tailrec fun digits(n: Int, a: Int = 0, f: (Int, Int) -> Int): Int =
        if (n == 0) a else digits(n / 10, f(a, n % 10), f)

    //вызовы через лямбды
    fun sumd(n: Int): Int = digits(n, 0) { a, b -> (a + b) }
    fun muld(n: Int): Int = digits(n, 1) { a, b -> (a * b) }
    fun maxd(n: Int): Int = digits(n / 10, n % 10) { a, b -> if (a > b) a else b }
    fun mind(n: Int): Int = digits(n / 10, n % 10) { a, b -> if (a < b) a else b }

    fun main() {
        println("Hello World!")
        val scanner = Scanner(`in`)
        val x: Int = scanner.nextInt()
        println(sumcd(x))
    }
}

fun main() = Main().main()