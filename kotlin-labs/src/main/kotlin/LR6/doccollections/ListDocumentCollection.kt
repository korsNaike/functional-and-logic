package LR6.doccollections

import LR6.DrivingLicense

class ListDocumentCollection(val documents: List<DrivingLicense>) :
    DocumentCollection<DrivingLicense>() {
    override fun searchDoc(doc: DrivingLicense): DrivingLicense? {
        return search(doc, documents.size)
    }

    private tailrec fun search(doc: DrivingLicense, max: Int, index: Int = 0): DrivingLicense? {
        if (index >= max) {
            return null
        }

        if (documents[index] == doc) {
            return doc
        } else {
            return search(doc, max, index + 1)
        }
    }
}