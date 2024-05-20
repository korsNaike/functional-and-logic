package LR6.doccollections

import LR6.DrivingLicense
import LR6.doccollections.treeset.TreeSet
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
//    generateTestDataToFile("documentSeriesNumbers.txt", 50000)
    println("файл готов")
    val docs = File("documentSeriesNumbers.txt").readLines().map { line ->
        val data = line.split(",")
        val inspiredDate = data[2].split(".")
        val expiredDate = data[3].split(".")
        DrivingLicense(
            data[0],
            data[1],
            LocalDate.of(
                inspiredDate[2].toInt(),
                inspiredDate[1].toInt(),
                inspiredDate[0].toInt(),
            ),
            LocalDate.of(
                expiredDate[2].toInt(),
                expiredDate[1].toInt(),
                expiredDate[0].toInt(),
            ),
        )
    }

    val arrayCollection = ArrayDocumentCollection(docs.toTypedArray())
    println("ArrayDocumentCollection готов")
    val listCollection = ListDocumentCollection(docs)
    println("ListDocumentCollection готов")
    val binaryListCollection = BinaryListDocumentCollection(docs)
    println("BinaryListDocumentCollection готов")
    val hashSetCollection = HashSetDocumentCollection(docs.toHashSet())
    println("HashSetDocumentCollection готов")
    val treeSetCollection = TreeSet().from(docs)
    println("TreeSet готов")

    val positiveSearchTimes = mutableMapOf<DocumentCollection<DrivingLicense>, MutableList<Long>>()

    var i = 0
    for (doc in docs) {
        val searchResults = mapOf(
            arrayCollection to arrayCollection.measureSearchTime(doc),
            listCollection to listCollection.measureSearchTime(doc),
            binaryListCollection to binaryListCollection.measureSearchTime(doc),
            hashSetCollection to hashSetCollection.measureSearchTime(doc),
            treeSetCollection to treeSetCollection.measureSearchTime(doc)
        )
        println("Нашли $i")
        i += 1

        val positiveSearchTime = searchResults.mapValues { it.value }

        positiveSearchTime.forEach { (collection, time) ->
            positiveSearchTimes.getOrPut(collection) { mutableListOf() }.add(time)
        }
    }

    println("\nСреднее время ответа:")
//    Среднее время ответа:
//    ArrayDocumentCollection: 0.57846 мс
//    ListDocumentCollection: 0.6556 мс
//    BinaryListDocumentCollection: 0.0117 мс
//    HashSetDocumentCollection: 2.2515 мс
//    TreeSet: 0.0549 мс
    positiveSearchTimes.forEach { (collection, times) ->
        val averageTime = times.average()
        println("${collection.javaClass.simpleName}: $averageTime мс")
    }
}

fun generateTestDataToFile(filename: String, count: Int) {
    val random = Random()
    val seriesLength = 4
    val numberLength = 6
    val numbers = "0123456789"
    val file = File(filename)
    if (file.exists()) {
        file.delete()
    }
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    for (i in 1..count) {
        val series = (1..seriesLength).map { numbers[random.nextInt(numbers.length)] }.joinToString("")
        val number = (1..numberLength).map { numbers[random.nextInt(numbers.length)] }.joinToString("")
        val startDate = LocalDate.ofEpochDay(
            random.nextInt(
                (LocalDate.now().minusYears(23).toEpochDay() - LocalDate.of(2000, 1, 1).toEpochDay()).toInt()
            ) + LocalDate.of(2000, 1, 1).toEpochDay()
        )
        val endDate = startDate.plusYears(5)
        val line = "$series,$number,${startDate.format(formatter)},${endDate.format(formatter)}\n"
        file.appendText(line)
    }
}
