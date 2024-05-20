package LR6.doccollections

import LR6.DrivingLicense

class HashSetDocumentCollection(private val documents: HashSet<DrivingLicense>)
    : DocumentCollection<DrivingLicense>() {

    override fun searchDoc(doc: DrivingLicense): DrivingLicense? {
        return documents.find { it == doc }
    }
}