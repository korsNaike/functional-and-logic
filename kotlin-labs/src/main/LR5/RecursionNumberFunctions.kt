class RecursionNumberFunctions {

    /**
     * Нахождение минимальной цифры числа с помощью рекурсии вверх
     */
    fun findMinDigit(number: Int): Int {
        return if (number < 10) {
            number
        } else {
            val digit = number % 10
            val minInRest = findMinDigit(number / 10)
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
            val digit = number % 10

            var newMinDigit = digit
            if (minDigit != null) {
                newMinDigit = if (digit < minDigit) digit else minDigit
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
            number < 10 -> if (number % 5 != 0) number else 0 // Если число делится на 5, то произведение равно 0
            else -> {
                val digit = number % 10
                val productInRest = productOfNonDivisibleBy5(number / 10)
                if (digit % 5 != 0) digit * productInRest else productInRest
            }
        }
    }

    /**
     * Нахождение произведения цифр числа, не делящихся на 5, с помощью хвостовой рекурсии
     */
    tailrec fun productOfNonDivisibleBy5Tail(number: Int, product: Int = 0): Int {
        return if (number == 0) {
            product
        } else {
            val digit = number % 10
            val newProduct =
                if (digit % 5 != 0 && product == 0) digit else if (digit % 5 != 0) product * digit else product
            productOfNonDivisibleBy5Tail(number / 10, newProduct)
        }
    }

    /**
     * Нахождение НОД двух чисел с помощью хвостовой рекурсии
     */
    tailrec fun nod(a: Int, b: Int): Int = if (b == 0) a else nod(b, a % b)

}