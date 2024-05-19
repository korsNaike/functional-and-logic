package javalr6;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class DrivingLicenseTest {

    private final DrivingLicense document1 = new DrivingLicense("1234", "123456", LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1));
    private final DrivingLicense document2 = new DrivingLicense("1234", "123456", LocalDate.of(2023, 2, 1), LocalDate.of(2026, 2, 1));
    private final DrivingLicense document3 = new DrivingLicense("5678", "234567", LocalDate.of(2022, 3, 1), LocalDate.of(2025, 3, 1));

    @Test
    void testEquals() {
        assertEquals(document1, document2);
        assertNotEquals(document1, document3);
    }

    @Test
    void testOrder() {
        Set<DrivingLicense> documentsByIssueDate = new TreeSet<>(Comparator.comparing(DrivingLicense::getIssueDate));
        documentsByIssueDate.add(document3);
        documentsByIssueDate.add(document1);
        documentsByIssueDate.add(document2);
        assertEquals(documentsByIssueDate.iterator().next(), document1);

        Set<DrivingLicense> documentsBySeriesNumber = new TreeSet<>();
        documentsBySeriesNumber.add(document3);
        documentsBySeriesNumber.add(document2);
        documentsBySeriesNumber.add(document1);
        assertEquals(documentsBySeriesNumber.toArray()[documentsBySeriesNumber.size() - 1], document3);
    }

    @Test
    void testHashSet() {
        Set<DrivingLicense> documentSet = new HashSet<>();
        documentSet.add(document1);
        documentSet.add(document2);
        documentSet.add(document3);
        assertEquals(2, documentSet.size());
        assertTrue(documentSet.contains(document1));
        assertFalse(documentSet.contains(new DrivingLicense("9999", "999999", LocalDate.now(), LocalDate.now().plusYears(5))));
    }
}
