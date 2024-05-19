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
}