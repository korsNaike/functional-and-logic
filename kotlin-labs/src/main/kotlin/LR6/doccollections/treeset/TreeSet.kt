package LR6.doccollections.treeset

import LR6.DrivingLicense
import LR6.doccollections.DocumentCollection

class TreeSet(var root: Node? = null) : DocumentCollection<DrivingLicense>() {

    fun insert(value: DrivingLicense) {
        val node = Node(value)
        if (root == null) {
            root = node
        } else {
            insert(root, node)
        }
    }

    fun insert(node: Node?, insertedNode: Node): Node {
        if (node == null) {
            return insertedNode
        }

        val cmp = insertedNode.value.compareTo(node.value)
        if (cmp == 0) {
            return node
        } else if (cmp == -1) {
            if (node.left == null) {
                node.left = insert(node.left, insertedNode)
            } else {
                insert(node.left, insertedNode)
            }
        } else {
            if (node.right == null) {
                node.right = insert(node.right, insertedNode)
            } else {
                insert(node.right, insertedNode)
            }
        }

        return node
    }

    fun from(list: List<DrivingLicense>): TreeSet {
        root = null
        return from(list, list.size, 0)
    }

    private tailrec fun from(list: List<DrivingLicense>, maxInd: Int, index: Int): TreeSet {
        if (index >= maxInd) {
            return this
        }

        this.insert(list[index])
        return this.from(list, maxInd,index + 1)
    }

    fun toList(): List<DrivingLicense> {
        return toList(root)
    }

    fun toList(node: Node?): List<DrivingLicense> {
        val list = mutableListOf<DrivingLicense>()
        if (node != null) {
            list.addAll(toList(node.left))
            list.add(node.value)
            list.addAll(toList(node.right))
        }
        return list
    }

    override fun searchDoc(doc: DrivingLicense): DrivingLicense? {
        return searchDoc(root, doc)
    }

    private fun searchDoc(node: Node?, doc: DrivingLicense): DrivingLicense? {
        if (node == null) {
            return null // Документ не найден
        }

        val cmp = doc.compareTo(node.value)
        return when {
            cmp == 0 -> node.value // Документ найден
            cmp < 0 -> searchDoc(node.left, doc) // Поиск в левом поддереве
            else -> searchDoc(node.right, doc) // Поиск в правом поддереве
        }
    }

}