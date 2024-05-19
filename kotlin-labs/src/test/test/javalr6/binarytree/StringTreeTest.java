package javalr6.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTreeTest {

    @Test
    void test() {
        List<String> list = Arrays.asList("This is a sentence", "Hello world", "Kotlin is awesome", "I love programming", "OneBiggestWordInTheUniverse");

        BiFunction<String, String, Integer> comparator1 = (o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            else if (o1 == null) return -1;
            else if (o2 == null) return 1;
            else {
                String[] splitO1 = o1.split(" ");
                String[] splitO2 = o2.split(" ");
                int sizeO1 = splitO1.length;
                int sizeO2 = splitO2.length;

                if (sizeO1 > sizeO2) return 1;
                else if (sizeO1 < sizeO2) return -1;
                else return 0;
            }
        };

        StringTree tree = StringTree.createTree(comparator1).from(list);
        assertEquals("OneBiggestWordInTheUniverse", tree.toList().getFirst());

        BiFunction<String, String, Integer> comparator2 = (o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            else if (o1 == null) return -1;
            else if (o2 == null) return 1;
            else {
                if (o1.length() > o2.length()) return 1;
                else if (o1.length() < o2.length()) return -1;
                else return 0;
            }
        };

        tree.setComparator(comparator2);
        assertEquals("Hello world", tree.from(list).toList().getFirst());
    }
}

