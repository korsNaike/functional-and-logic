package LR6.binarytree

class StringComparator(val compareFunc: (String?, String?) -> Int) : Comparator<String> {
    override fun compare(o1: String?, o2: String?): Int {
        return compareFunc(o1, o2);
    }

}