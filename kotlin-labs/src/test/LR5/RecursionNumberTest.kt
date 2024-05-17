import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class RecursionNumberTest {

    @Test
    fun testFindMinDigit() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(2, recursionFunc.findMinDigit(234))
        assertEquals(0, recursionFunc.findMinDigit(0))
        assertEquals(1, recursionFunc.findMinDigit(54321))
        assertEquals(3, recursionFunc.findMinDigit(999939939))
    }

    @Test
    fun testFindMinDigitTailRec() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(2, recursionFunc.findMinDigitTailRec(234))
        assertEquals(0, recursionFunc.findMinDigitTailRec(0))
        assertEquals(1, recursionFunc.findMinDigitTailRec(54321))
        assertEquals(3, recursionFunc.findMinDigitTailRec(999939939))
    }

    @Test
    fun testProductOfNonDivisibleBy5() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(6, recursionFunc.productOfNonDivisibleBy5(123))
        assertEquals(32, recursionFunc.productOfNonDivisibleBy5(854))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5(555))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5(0))
        assertEquals(1, recursionFunc.productOfNonDivisibleBy5(1))
    }

    @Test
    fun testProductOfNonDivisibleBy5Tail() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(6, recursionFunc.productOfNonDivisibleBy5Tail(123))
        assertEquals(32, recursionFunc.productOfNonDivisibleBy5Tail(854))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5Tail(555))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5Tail(0))
        assertEquals(1, recursionFunc.productOfNonDivisibleBy5Tail(1))
    }

    @Test
    fun testNod() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(12, recursionFunc.nod(24, 36))
        assertEquals(5, recursionFunc.nod(15, 10))
        assertEquals(7, recursionFunc.nod(14, 21))
    }

    @Test
    fun findMax() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(
            32,
            recursionFunc.findMax(
                listOf(123, 854, 555), recursionFunc::productOfNonDivisibleBy5Tail)
        )
        assertEquals(
            5,
            recursionFunc.findMax(
                listOf(123, 854, 555),
                recursionFunc::findMinDigitTailRec
            )
        )
    }

}