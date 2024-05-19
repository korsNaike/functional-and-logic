package LR6.binarytree

fun main() {
    val list = listOf("This is a sentence", "Hello world", "Kotlin is awesome", "I love programming", "OneBiggestWordInTheUniverse")

    val comparator = StringComparator { o1, o2 ->
        if (o1 == null && o2 == null) 0
        else if (o1 == null) -1
        else if (o2 == null) 1
        else {
            val splitO1 = o1.split(' ')
            val splitO2 = o2.split(' ')
            val sizeO1 = splitO1.size
            val sizeO2 = splitO2.size

            if (sizeO1 > sizeO2) 1
            else if (sizeO1 < sizeO2) -1
            else 0
        }
    }

    val tree = StringTree(comparator = comparator).from(list)
    println("По количеству слов:")
    println(tree.toList())
    tree.comparator = StringComparator { o1, o2 ->
        if (o1 == null && o2 == null) 0
        else if (o1 == null) -1
        else if (o2 == null) 1
        else {
            if (o1.length > o2.length) 1
            else if (o1.length < o2.length) -1
            else 0
        }
    }
    println("По длине строк:")
    println(tree.from(list).toList())
}