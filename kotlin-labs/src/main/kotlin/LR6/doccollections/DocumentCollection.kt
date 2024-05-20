package LR6.doccollections

import java.time.LocalDate
import LR6.DrivingLicense

abstract class DocumentCollection<Doc : DrivingLicense> {
    abstract fun searchDoc(doc: Doc): Doc?

    fun measureSearchTime(doc: Doc): Long {
        val startTime = System.currentTimeMillis()
        searchDoc(doc)
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }
}