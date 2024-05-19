package javalr6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListFunctionsTest {

    @Test
    void quadraList() {
        assertEquals(3, ListFunctions.quadraList(Arrays.asList(1, 4, 9, 3, 2)));
        assertEquals(0, ListFunctions.quadraList(List.of()));
        assertEquals(1, ListFunctions.quadraList(Arrays.asList(2, 10, 9, 4)));
    }

    @Test
    void createTripletList() {
        //в джаве у обычных списков нет реализации сравнения, поэтому fail
        List<ListFunctions.Triple<Integer, Integer, Integer>> result = Arrays.asList(
                new ListFunctions.Triple<>(31, 13, 16),
                new ListFunctions.Triple<>(26, 131, 8),
                new ListFunctions.Triple<>(11, 7, 11)
        );

        assertEquals(result, ListFunctions.createTripletList(
                Arrays.asList(31, 11, 26),
                Arrays.asList(13, 131, 7),
                Arrays.asList(8, 16, 11)
        ));
    }

    @Test
    void testElementAtIndexIsMinimum() {
        List<Integer> testList = Arrays.asList(5, 3, 7, 1, 9);
        int index = 3;
        boolean result = ListFunctions.indexIsGlobalMin(testList, index);
        assertTrue(result);
    }

    @Test
    void testCountOfEqualsElements() {
        assertEquals(4, ListFunctions.countOfEqualsElements(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 2, 3, 4)));
        assertEquals(0, ListFunctions.countOfEqualsElements(Arrays.asList(5, 6, 7, 8), Arrays.asList(1, 2, 3, 4)));
        assertEquals(0, ListFunctions.countOfEqualsElements(List.of(), Arrays.asList(1, 2, 3, 4)));
        assertEquals(1, ListFunctions.countOfEqualsElements(Arrays.asList(1, 2), List.of(2)));
    }

    @Test
    void testFindCountMinInInterval() {
        assertEquals(3, ListFunctions.findCountMinInInterval(
                Arrays.asList(1, 2, 3, 4, 4, 4, 5, 6, 7),
                new ListFunctions.Pair<>(4, 7))
        );
        assertEquals(1, ListFunctions.findCountMinInInterval(
                Arrays.asList(1, 2, 3, 4, 4, 4, 5, 6, 7),
                new ListFunctions.Pair<>(1, 5))
        );
    }

    @Test
    void testGetBetweenMaxList() {
        //в джаве у обычных списков нет реализации сравнения, поэтому fail
        assertEquals(
                Arrays.asList(4, 5, 6),
                ListFunctions.getBetweenMaxList(Arrays.asList(1, 2, 3, 4, 7, 4, 5, 6, 7))
        );
        assertEquals(
                Arrays.asList(4, 3, 4, 5, 1),
                ListFunctions.getBetweenMaxList(Arrays.asList(1, 2, 8, 4, 3, 4, 5, 1, 7))
        );
    }

    @Test
    void testIsMaxInInterval() {
        assertFalse(ListFunctions.isMaxInInterval(Arrays.asList(1, 3, 4, 5, 7, 8), new ListFunctions.Pair<>(1, 5)));
        assertTrue(ListFunctions.isMaxInInterval(Arrays.asList(1, 3, 4, 5, 7, 8), new ListFunctions.Pair<>(5, 10)));
    }

    @Test
    void testCountLocalMaxima() {
        assertEquals(3, ListFunctions.countLocalMaxima(Arrays.asList(1, 3, 2, 5, 4, 6, 2)));
        assertEquals(3, ListFunctions.countLocalMaxima(Arrays.asList(3, 2, 5, 4, 6, 2)));
        assertEquals(3, ListFunctions.countLocalMaxima(Arrays.asList(3, 2, 5, 4, 2, 6)));
    }
}

