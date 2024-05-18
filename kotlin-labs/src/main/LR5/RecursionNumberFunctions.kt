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

}