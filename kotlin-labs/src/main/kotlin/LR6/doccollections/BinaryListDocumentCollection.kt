package LR6.doccollections

import LR6.DrivingLicense

class BinaryListDocumentCollection(private val documents: List<DrivingLicense>) :
    DocumentCollection<DrivingLicense>() {

    override fun searchDoc(doc: DrivingLicense): DrivingLicense? {
        return binarySearch(doc, 0, documents.size)
    }

    private tailrec fun binarySearch(doc: DrivingLicense, low: Int, high: Int): DrivingLicense? {
        if (low > high) {
            return null // элемент не найден
        }

        val mid = low + (high - low) / 2
        val curDoc = documents[mid]
        val compareRes = doc.compareTo(curDoc)

        return when (compareRes) {
            0 -> curDoc // элемент найден
            1 -> binarySearch(doc, mid + 1, high) // поиск в правой половине
            else -> binarySearch(doc, low, mid - 1) // поиск в левой половине
        }
    }
}