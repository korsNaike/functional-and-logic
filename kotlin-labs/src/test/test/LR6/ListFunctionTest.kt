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
}