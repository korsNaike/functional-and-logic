package LR5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CycleFunctionsTest {

    @Test
    fun testFindMinDigitSuccess() {
        val main = CycleFunctions()
        assertEquals(1, main.findMinDigit(12345))
        assertEquals(9, main.findMinDigit(999999))
    }

    @Test
    fun testProductOfNonFiveDigits() {
        val main = CycleFunctions()
        assertEquals(24, main.productOfNonFiveDigits(12345)) // Ожидаемый результат: 1*2*3*4 = 24
        assertEquals(1, main.productOfNonFiveDigits(10000)) // Ожидаемый результат: 1
        assertEquals(3024, main.productOfNonFiveDigits(56789)) // Ожидаемый результат: 6*7*8*9 = 3024
    }

    @Test
    fun testNOD() {
        val main = CycleFunctions()
        assertEquals(6, main.nod(24, 18)) // Ожидаемый результат: 6
        assertEquals(1, main.nod(17, 5)) // Ожидаемый результат: 1
        assertEquals(4, main.nod(20, 28)) // Ожидаемый результат: 4
    }

}