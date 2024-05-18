import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.abs;

class RecursionNumberFunctions {

    /**
     * Нахождение минимальной цифры числа с помощью рекурсии вверх
     */
    fun findMinDigit(number: Int): Int {
        return if (abs(number) < 10) {
            number
        } else {
            val digit = abs(number % 10)
            val minInRest = abs(findMinDigit(number / 10))
            if (digit < minInRest) digit else minInRest
        }
    }

    /**
     * Нахождение минимальной цифры числа с помощью хвостовой рекурсии
     */
    tailrec fun findMinDigitTailRec(number: Int, minDigit: Int? = null): Int {
        return if (number == 0) {
            minDigit ?: number
        } else {
            val digit = abs(number % 10)

            var newMinDigit = digit
            if (minDigit != null) {
                newMinDigit = abs(if (digit < minDigit) digit else minDigit)
            }

            findMinDigitTailRec(number / 10, newMinDigit)
        }
    }

    /**
     * Нахождение произведения цифр числа, не делящихся на 5, с помощью рекурсии вверх
     */
    fun productOfNonDivisibleBy5(number: Int): Int {
        return when {
            number == 0 -> 0 // Базовый случай: если число равно 0, то произведение равно 0
            abs(number) < 10 -> if (number % 5 != 0) abs(number) else 0 // Если число делится на 5, то произведение равно 0
            else -> {
                val digit = abs(number % 10)
                val productInRest = abs(productOfNonDivisibleBy5(number / 10))
                if (digit % 5 != 0) digit * productInRest else productInRest
            }
        }
    }

    /**
     * Нахождение произведения цифр числа, не делящихся на 5, с помощью хвостовой рекурсии
     */
    tailrec fun productOfNonDivisibleBy5Tail(number: Int, product: Int = 0): Int {
        return if (number == 0) {
            abs(product)
        } else {
            val digit = number % 10
            val newProduct = abs(
                if (digit % 5 != 0 && product == 0) digit else if (digit % 5 != 0) product * digit else product
            )
            productOfNonDivisibleBy5Tail(number / 10, newProduct)
        }
    }

    /**
     * Нахождение НОД двух чисел с помощью хвостовой рекурсии
     */
    tailrec fun nod(a: Int, b: Int): Int = if (b == 0) abs(a) else nod(b, a % b)

    /**
     * Функция высших порядков для нахождения максимума среди чисел в списке по переданной функции
     */
    fun findMax(numbers: List<Int>, callbackFun: (Int) -> Int): Int {
        return findMax(numbers, callbackFun, 0, callbackFun(numbers[0]))
    }

    /**
     * Хвостовая рекурсия функции высших порядков для нахождения максимума
     */
    private tailrec fun findMax(
        numbers: List<Int>,
        callbackFun: (Int) -> Int,
        index: Int,
        max: Int
    ): Int {
        if (index == numbers.size)
            return max

        val callbackValue = callbackFun(numbers[index])
        val newMax = if (max > callbackValue) max else callbackValue

        return findMax(
            numbers,
            callbackFun,
            index + 1,
            newMax
        )
    }

    fun getFunction(functionName: String): ((Int) -> Int)? {
        return when (functionName) {
            "findMinDigit" -> ::findMinDigit
            "findMinDigitTailRec" -> ::findMinDigitTailRec
            "productOfNonDivisibleBy5" -> ::productOfNonDivisibleBy5
            "productOfNonDivisibleBy5Tail" -> ::productOfNonDivisibleBy5Tail
            else -> null
        }
    }

    /**
     * Максимальный простой делитель
     */
    tailrec fun maxPrimeDivisor(n: Int, divisor: Int = 2): Int {
        return when {
            divisor * divisor > abs(n) -> abs(n)
            abs(n) % divisor == 0 -> maxPrimeDivisor(abs(n) / divisor, divisor)
            else -> maxPrimeDivisor(n, divisor + 1)
        }
    }

    /**
     * Нахождение НОД максимального нечетного непростого делителя и произведения цифр числа, не делящихся на 5
     */
    fun calculateNodAndProductWithOddNonPrimeDivisor(number: Int): Int =
        nod(
            findMaxOddNonPrimeDivisor(number),
            productOfNonDivisibleBy5Tail(number)
        )

    /**
     * Нахождение максимального нечетного непростого делителя числа
     */
    fun findMaxOddNonPrimeDivisor(number: Int): Int = findMax(findDivisors(number)) { divisor ->
        if (divisor % 2 != 0 && !isPrime(divisor)) divisor else -1
    }

    /**
     * Нахождение всех делителей числа
     */
    private fun findDivisors(number: Int): List<Int> {
        return if (number == 1) {
            listOf(1)
        } else {
            findDivisors(number, number - 1, mutableListOf())
        }
    }

    /**
     * Хвостовая рекурсия для нахождения всех делителей числа
     */
    private tailrec fun findDivisors(
        originalNumber: Int,
        currentDivisor: Int,
        divisors: MutableList<Int>
    ): List<Int> {
        if (currentDivisor == 0) {
            return divisors
        }

        if (originalNumber % currentDivisor == 0) {
            divisors.add(currentDivisor)
        }

        return findDivisors(originalNumber, currentDivisor - 1, divisors)
    }

    /**
     * Проверка числа на простоту
     */
    private fun isPrime(number: Int, divisor: Int = 2): Boolean {
        if (number <= 2) {
            return number == 2
        }
        if (number % divisor == 0) {
            return false
        }
        if (divisor * divisor > number) {
            return true
        }
        return isPrime(number, divisor + 1)
    }

    /**
     * Найти период дроби
     */
    fun findPeriod(number: BigInteger, period: Int = 1): Int {
        return if (
            BigInteger("10")
                .pow(period)
                .minus(BigInteger("1"))
                .mod(number) == BigInteger("0")
        )
            period
        else findPeriod(number, period + 1)
    }

    /**
     * Найти число у которого дробь 1/n имеет максимальный период
     */
    tailrec fun maxPeriod(currentNumber: Int = 0, max: Int = 0): Int {
        return if (currentNumber > 1000) {
            max + 1
        } else if (
            currentNumber % 2 != 0 &&
            currentNumber % 5 != 0 &&
            nod(10, currentNumber) == 1
        ) {
            val period = findPeriod(BigInteger(currentNumber.toString()))
            if (period > max) {
                maxPeriod(currentNumber + 1, period)
            } else {
                maxPeriod(currentNumber + 1, max)
            }
        } else {
            maxPeriod(currentNumber + 1, max)
        }
    }


}

fun main() {
    val rec = RecursionNumberFunctions()
    println(rec.maxPeriod())
}