class CycleFunctions {

    /**
     * Найти минимальную цифру в числе
     */
    fun findMinDigit(number: Int): Int {
        var minDigit = 9
        var temp = number
        while (temp > 0) {
            val digit = temp % 10
            if (digit < minDigit) {
                minDigit = digit
            }
            temp /= 10
        }
        return minDigit
    }

    /**
     * Произведение всех цифр числа, не делящихся на 5
     */
    fun productOfNonFiveDigits(number: Int): Int {
        var product = 1
        var temp = number
        while (temp > 0) {
            val digit = temp % 10
            if (digit != 0 && digit % 5 != 0) {
                product *= digit
            }
            temp /= 10
        }
        return product
    }

    /**
     * Найти НОД двух чисел
     */
    fun nod(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

}