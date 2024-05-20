package LR6.doccollections

import LR6.DrivingLicense
import java.util.TreeSet

class TreeSetDocumentCollection(private val documents: TreeSet<DrivingLicense>) :
    DocumentCollection<DrivingLicense>() {

    override fun searchDoc(doc: DrivingLicense): DrivingLicense? {
        return documents.find { it == doc }
    }
}