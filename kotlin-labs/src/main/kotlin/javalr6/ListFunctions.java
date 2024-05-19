package javalr6;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ListFunctions {

    private static <T> T arrayOP(List<Integer> m, BiFunction<T, Integer, T> f, T a, int i) {
        return i <= 0 ? a : arrayOP(m, f, f.apply(a, m.get(i - 1)), i - 1);
    }

    private static boolean checkList(List<Integer> list, Predicate<Integer> predicate, int index) {
        return index > 0 && (predicate.test(list.get(index - 1)) || checkList(list, predicate, index - 1));
    }

    /**
     * Сколько элементов из списка могут быть квадратами какого-то из элементов списка
     */
    static int quadraList(List<Integer> list) {
        return arrayOP(list, (total, element) ->
                checkList(list, i -> i == element * element, list.size()) ? total + 1 : total, 0, list.size());
    }

    /**
     * Создать список из кортежей по 3 элемента по специальной сортировке
     */
    static List<Triple<Integer, Integer, Integer>> createTripletList(List<Integer> list1, List<Integer> list2, List<Integer> list3) {
        return IntStream.range(0, list1.size())
                .mapToObj(index -> new Triple<>(
                        list1.get(list1.size() - index - 1),
                        getElementByIndex(list2, index, ListFunctions::sumDigits),
                        getElementByIndex(list3, index, ListFunctions::countDivisors, Comparator.reverseOrder())
                ))
                .collect(Collectors.toList());
    }

    private static <T> T getElementByIndex(List<T> list, int index, Function<T, Integer> function) {
        return getElementByIndex(list, index, function, Comparator.naturalOrder());
    }

    private static <T> T getElementByIndex(List<T> list, int index, Function<T, Integer> function, Comparator<Integer> comparator) {
        return list.stream()
                .sorted((a, b) -> comparator.compare(function.apply(a), function.apply(b)))
                .skip(index)
                .findFirst()
                .orElseThrow();
    }

    /**
     * Сумма цифр
     */
    static int sumDigits(int number) {
        return forDigits(number, Integer::sum, digit -> true);
    }

    private static int forDigits(int number, BiFunction<Integer, Integer, Integer> f, Predicate<Integer> predicate) {
        return number == 0 ? 0 : predicate.test(number % 10) ? f.apply(forDigits(number / 10, f, predicate), number % 10) : forDigits(number / 10, f, predicate);
    }

    /**
     * Количество делителей
     */
    static int countDivisors(int n) {
        return (int) IntStream.rangeClosed(1, n)
                .filter(i -> n % i == 0)
                .count();
    }

    /**
     * Определить, является ли элемент по индексу глобальным минимумом
     */
    static boolean indexIsGlobalMin(List<Integer> list, int index) {
        return list.get(index) == list.stream().mapToInt(i -> i).min().orElseThrow();
    }

    /**
     * Посчитать количество совпадающих по значению элементов
     */
    static int countOfEqualsElements(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);

        return (int) set1.stream()
                .filter(set2::contains)
                .count();
    }

    /**
     * Найти количество минимумов в интервале
     */
    static int findCountMinInInterval(List<Integer> list, Pair<Integer, Integer> interval) {
        List<Integer> filterList = list.stream()
                .filter(i -> i >= interval.getFirst() && i <= interval.getSecond())
                .toList();

        int minimum = filterList.stream()
                .mapToInt(i -> i)
                .min()
                .orElseThrow();

        return (int) filterList.stream()
                .filter(i -> i == minimum)
                .count();
    }

    /**
     * Получить список элементов, которые находятся между первым и вторым максимумом
     */
    static List<Integer> getBetweenMaxList(List<Integer> list) {
        int maxTo = list.indexOf(list.stream().mapToInt(i -> i).max().orElseThrow());
        List<Integer> listWithoutFirstMax = list.stream()
                .filter(i -> !Objects.equals(i, list.get(maxTo)))
                .toList();

        int maxFrom = listWithoutFirstMax.indexOf(listWithoutFirstMax.stream().mapToInt(i -> i).max().orElseThrow());
        maxFrom = maxFrom > maxTo ? maxFrom + 1 : maxFrom;

        int finalMaxFrom = maxFrom;
        return maxTo > maxFrom
                ? list.stream().filter(i -> list.indexOf(i) > finalMaxFrom && list.indexOf(i) < maxTo).collect(Collectors.toList())
                : list.stream().filter(i -> list.indexOf(i) < finalMaxFrom && list.indexOf(i) > maxTo).collect(Collectors.toList());
    }

    /**
     * Проверить наличие максимального элемента в интервале
     */
    static boolean isMaxInInterval(List<Integer> list, Pair<Integer, Integer> interval) {
        List<Integer> filterList = list.stream()
                .filter(i -> i >= interval.getFirst() && i <= interval.getSecond())
                .toList();

        int maximum = list.stream()
                .mapToInt(i -> i)
                .max()
                .orElseThrow();

        return filterList.contains(maximum);
    }

    /**
     * Посчитать количество локальных максимумов
     */
    static int countLocalMaxima(List<Integer> list) {
        return IntStream.range(0, list.size())
                .filter(index -> {
                    int curr = list.get(index);
                    int prev = index == 0 ? Integer.MIN_VALUE : list.get(index - 1);
                    int next = index == list.size() - 1 ? Integer.MIN_VALUE : list.get(index + 1);
                    return curr > prev && curr > next;
                })
                .toArray().length;
    }

    public static class Pair<T, U> {
        private final T first;
        private final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }

            Pair<T, U> triple = (Pair<T, U>) obj;
            return triple.getFirst() == this.getFirst() &&
                    triple.getSecond() == this.getSecond();
        }
    }

    public static class Triple<T, U, V> extends Pair<T, U> {
        private final V third;

        public Triple(T first, U second, V third) {
            super(first, second);
            this.third = third;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }

            Triple<T, U, V> triple = (Triple<T, U, V>) obj;
            return triple.getFirst() == this.getFirst() &&
                    triple.getSecond() == this.getSecond() &&
                    triple.getThird() == this.getThird();
        }

        public V getThird() {
            return third;
        }
    }

    private interface Function<T, R> {
        R apply(T t);
    }
}


