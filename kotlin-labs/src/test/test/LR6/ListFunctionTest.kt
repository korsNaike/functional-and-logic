package LR6

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ListFunctionTest {

    @Test
    fun quadraList() {
        val listFunctions = ListFunctions()
        assertEquals(3, listFunctions.quadraList(listOf(1, 4, 9, 3, 2)))
        assertEquals(0, listFunctions.quadraList(listOf()))
        assertEquals(1, listFunctions.quadraList(listOf(2, 10, 9, 4)))
    }

    @Test
    fun createTripletList() {
        val listFunctions = ListFunctions()
        val result = listOf(
            Triple(31, 13, 16),
            Triple(26, 131, 8),
            Triple(11, 7, 11),
        )

        assertEquals(result, listFunctions.createTripletList(
            listOf(31, 11, 26),
            listOf(13, 131, 7),
            listOf(8, 16, 11)
        ))
    }

    @Test
    fun testElementAtIndexIsMinimum() {
        val listFunctions = ListFunctions()
        val testList = listOf(5, 3, 7, 1, 9)
        val index = 3
        val result = listFunctions.indexIsGlobalMin(testList, index)
        assertEquals(true, result)
    }

    @Test
    fun testCountOfEqualsElements() {
        val listFunctions = ListFunctions()
        assertEquals(4, listFunctions.countOfEqualsElements(listOf(1, 2, 3, 4), listOf(1, 2, 3, 4)))
        assertEquals(0, listFunctions.countOfEqualsElements(listOf(5, 6, 7, 8), listOf(1, 2, 3, 4)))
        assertEquals(0, listFunctions.countOfEqualsElements(listOf(), listOf(1, 2, 3, 4)))
        assertEquals(1, listFunctions.countOfEqualsElements(listOf(1, 2), listOf(2)))
    }

    @Test
    fun testFindCountMinInInterval() {
        val listFunctions = ListFunctions()
        assertEquals(3, listFunctions.findCountMinInInterval(
            listOf(1, 2, 3, 4, 4, 4, 5, 6, 7),
            Pair(4, 7))
        )
        assertEquals(1, listFunctions.findCountMinInInterval(
            listOf(1, 2, 3, 4, 4, 4, 5, 6, 7),
            Pair(1, 5))
        )
    }

    @Test
    fun testGetBetweenMaxList() {
        val listFunctions = ListFunctions()
        assertEquals(
            listOf(4, 5, 6),
            listFunctions.getBetweenMaxList(listOf(1, 2, 3, 4, 7, 4, 5, 6, 7))
        )
        assertEquals(
            listOf(4, 3, 4, 5, 1),
            listFunctions.getBetweenMaxList(listOf(1, 2, 8, 4, 3, 4, 5, 1, 7))
        )
    }

    @Test
    fun testIsMaxInInterval() {
        val listFunctions = ListFunctions()
        assertEquals(
            false,
            listFunctions.isMaxInInterval(listOf(1, 3, 4, 5, 7, 8), Pair(1, 5))
        )

        assertEquals(
            true,
            listFunctions.isMaxInInterval(listOf(1, 3, 4, 5, 7, 8), Pair(5, 10))
        )
    }

    @Test
    fun testCountLocalMaxima() {
        val listFunctions = ListFunctions()
        assertEquals(3, listFunctions.countLocalMaxima(listOf(1, 3, 2, 5, 4, 6, 2)))
        assertEquals(3, listFunctions.countLocalMaxima(listOf(3, 2, 5, 4, 6, 2)))
        assertEquals(3, listFunctions.countLocalMaxima(listOf(3, 2, 5, 4, 2, 6)))
    }
}