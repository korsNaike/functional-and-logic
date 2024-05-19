package LR6

import java.time.LocalDate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

class DrivingLicenseTest {

    private val document1 = DrivingLicense("1234", "123456", LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1))
    private val document2 = DrivingLicense("1234", "123456", LocalDate.of(2023, 2, 1), LocalDate.of(2026, 2, 1))
    private val document3 = DrivingLicense("5678", "234567", LocalDate.of(2022, 3, 1), LocalDate.of(2025, 3, 1))

    @Test
    fun testEquals() {
        assertEquals(document1, document2)
        assertNotEquals(document1, document3)
    }

    @Test
    fun testOrder() {
        val documentsByIssueDate = TreeSet<DrivingLicense> { d1, d2 -> d1.issueDate.compareTo(d2.issueDate) }
        documentsByIssueDate.addAll(listOf(document3, document1, document2))
        assertEquals(documentsByIssueDate.first, document1)

        val documentsBySeriesNumber = TreeSet<DrivingLicense>()
        documentsBySeriesNumber.addAll(listOf(document3, document2, document1))
        assertEquals(documentsBySeriesNumber.last, document3)
    }

    @Test
    fun testHashSet() {
        val documentSet = HashSet<DrivingLicense>()
        documentSet.addAll(listOf(document1, document2, document3))
        assertEquals(2, documentSet.size)
        assertTrue(documentSet.contains(document1))
        assertFalse(documentSet.contains(
            DrivingLicense(
                "9999",
                "999999",
                LocalDate.now(),
                LocalDate.now().plusYears(5)
            )
        ))
    }
}