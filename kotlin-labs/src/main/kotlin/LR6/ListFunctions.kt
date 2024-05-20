package LR6

import LR5.RecursionNumberFunctions

class ListFunctions {

    tailrec fun arrayOP(m: List<Int>, f: (Int, Int) -> Int, i: Int = m.size, a: Int = 0): Int =
        if (i <= 0) a else arrayOP(m, f, i - 1, f(a, m[i - 1]))

    tailrec fun checkList(list: List<Int>, predicate: (Int) -> Boolean, index: Int = list.size): Boolean {
        return if (index <= 0) {
            false
        } else if (predicate(list[index - 1])) {
            true
        } else {
            checkList(list, predicate, index - 1)
        }
    }

    /**
     * Сколько элементов из списка могут быть квадратами какого-то из элементов списка
     */
    fun quadraList(list: List<Int>): Int {
        return arrayOP(list, { total, element ->
            if (checkList(list, { it == element * element })) total + 1 else total
        })
    }

    /**
     * Создать список из кортежей по 3 элемента по специальной сортировке
     */
    fun createTripletList(list1: List<Int>, list2: List<Int>, list3: List<Int>): List<Triple<Int, Int, Int>> {
        return list1.sortedDescending()
            .mapIndexed { index, value ->
                Triple(
                    value,
                    list2.sortedBy { sumDigits(it) }[index],
                    list3.sortedByDescending { countDivisors(it) }[index]
                )
            }
    }

    /**
     * Сумма цифр
     */
    fun sumDigits(number: Int): Int = RecursionNumberFunctions()
        .forDigits(number, { total, digit -> total + digit }, { true }
        )

    /**
     * Количество делителей
     */
    fun countDivisors(n: Int): Int = (1..n).count { n % it == 0 }

    /**
     * Определить, является ли элемент по индексу глобальным максимумом
     */
    fun indexIsGlobalMin(list: List<Int>, index: Int): Boolean = list[index] == list.minOf { it }

    /**
     * Посчитать количество совпадающих по значению элементов
     */
    fun countOfEqualsElements(list1: List<Int>, list2: List<Int>): Int {
        val set1 = list1.toSet()
        val set2 = list2.toSet()

        return set1.count { it in set2 }
    }

    /**
     * Найти количество минимумов в интервале
     */
    fun findCountMinInInterval(list: List<Int>, interval: Pair<Int, Int>): Int {
        val filterList = list.filter { it >= interval.first && it <= interval.second }
        println(filterList)
        val minimum = filterList.minOf { it }

        return filterList.count { it == minimum }
    }

    /**
     * Получить список элементов, которые находятся между первым и вторым максимумом
     */
    fun getBetweenMaxList(list: List<Int>): List<Int> {
        val maxTo = list.indexOf(list.maxOf { it })
        val listWithoutFirstMax = list.filterIndexed { index, i -> index != maxTo }
        val maxFromPre = listWithoutFirstMax.indexOf(listWithoutFirstMax.maxOf { it })
        val maxFrom = if (maxFromPre > maxTo) maxFromPre + 1 else maxFromPre

        return if (maxTo > maxFrom) {
            list.filterIndexed { index, _ -> index > maxFrom && index < maxTo }
        } else {
            list.filterIndexed { index, _ -> index < maxFrom && index > maxTo }
        }
    }

    /**
     * Проверить наличие максимального элемента в интервале
     */
    fun isMaxInInterval(list: List<Int>, interval: Pair<Int, Int>): Boolean {
        val filterList = list.filter { it >= interval.first && it <= interval.second }
        val maximum = list.maxOf { it }

        return filterList.contains(maximum)
    }

    /**
     * Посчитать количество локальных максимумов
     */
    fun countLocalMaxima(list: List<Int>): Int {
        return list.indices.count { index ->
            val curr = list[index]
            val prev = if (index == 0) Int.MIN_VALUE else list[index - 1]
            val next = if (index == list.lastIndex) Int.MIN_VALUE else list[index + 1]
            curr > prev && curr > next
        }
    }

    /**
     * Получить список, где идут сначала чётные индексы, а затем нечётные
     */
    fun firstEvenThenOdd(list: List<Int>): List<Int> {
        val evenList = list.filterIndexed { index, _ -> index % 2 == 0 }
        val oddList = list.filterIndexed { index, _ -> index % 2 != 0 }
        return evenList + oddList;
    }

    /**
     * Проверить чередование целых и вещественных
     */
    fun hasAlternation(numbers: List<Double>): Boolean {
        return if (numbers[0] % 1.0 == 0.0) {
            hasAlternation(numbers, 0, false)
        } else {
            hasAlternation(numbers, 0, true)
        }
    }

    /**
     * Проверить чередование целых и вещественных
     */
    fun hasAlternation(numbers: List<Double>, index: Int = 0, prevIsInt: Boolean): Boolean {
        if (numbers.size == index) return true

        val isInt = numbers[index] % 1.0 == 0.0
        return if (isInt == prevIsInt) {
            false
        } else {
            hasAlternation(numbers,index + 1, isInt)
        }
    }

    /**
     * Составить список из простых делителей числа, причём если на число можно разделить n раз,
     * то оно будет присутствовать в списке n раз.
     */
    fun findPrimeDivisors(number: Int, divisor: Int = 2): List<Int> {
        return when {
            number <= 1 -> emptyList()
            number % divisor == 0 ->  {
                val updatedNumber = number / divisor
                listOf(divisor) + findPrimeDivisors(updatedNumber, divisor)
            }
            else -> findPrimeDivisors(number, divisor + 1)
        }
    }

    /**
     * Посчитать количество элементов в списке, которые больше, чем сумма предыдущих
     */
    fun countElementsGreaterThanSumOfPrevious(inputList: List<Int>): Int {
        var sum = 0
        return inputList.count { el ->
            val isGreaterThanSum = el > sum
            sum += el
            isGreaterThanSum
        }
    }

}