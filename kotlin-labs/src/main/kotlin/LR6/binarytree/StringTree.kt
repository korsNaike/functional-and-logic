package LR6.binarytree

class StringTree(var comparator: Comparator<String>, var root: StringNode? = null) {

    fun insert(value: String) {
        val node = StringNode(value)
        if (root == null) {
            root = node
        } else {
            insert(root, node)
        }
    }

    fun insert(node: StringNode?, insertedNode: StringNode): StringNode {
        if (node == null) {
            return insertedNode
        }

        if (comparator.compare(insertedNode.value, node.value) < 1) {
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

    tailrec fun from(list: List<String>): StringTree {
        root = null
        return from(list, list.size, 0)
    }

    private tailrec fun from(list: List<String>, maxInd: Int, index: Int): StringTree {
        if (index >= maxInd) {
            return this
        }

        this.insert(list[index])
        return this.from(list, maxInd,index + 1)
    }

    fun toList(): List<String> {
        return toList(root)
    }

    fun toList(node: StringNode?): List<String> {
        val list = mutableListOf<String>()
        if (node != null) {
            list.addAll(toList(node.left))
            list.add(node.value)
            list.addAll(toList(node.right))
        }
        return list
    }

    fun sortByWordCount(list: List<String>): List<String> {
        return list.sortedBy { it.split("\\s+".toRegex()).size }
    }

}