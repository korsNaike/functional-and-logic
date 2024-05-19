package javalr6.binarytree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class StringTree {
    private Comparator<String> comparator;
    private StringNode root;

    public StringTree(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    public void setComparator(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    public void setComparator(BiFunction<String, String, Integer> compareFunc) {
        this.comparator = compareFunc::apply;
    }

    public void insert(String value) {
        StringNode node = new StringNode(value);
        if (root == null) {
            root = node;
        } else {
            root = insert(root, node);
        }
    }

    private StringNode insert(StringNode node, StringNode insertedNode) {
        if (node == null) {
            return insertedNode;
        }

        if (comparator.compare(insertedNode.getValue(), node.getValue()) < 0) {
            node.setLeft(insert(node.getLeft(), insertedNode));
        } else {
            node.setRight(insert(node.getRight(), insertedNode));
        }

        return node;
    }

    public StringTree from(List<String> list) {
        root = null;
        return fromHelper(list, 0);
    }

    private StringTree fromHelper(List<String> list, int index) {
        if (index >= list.size()) {
            return this;
        }

        insert(list.get(index));
        return fromHelper(list, index + 1);
    }

    public List<String> toList() {
        return toList(root);
    }

    private List<String> toList(StringNode node) {
        List<String> list = new ArrayList<>();
        if (node != null) {
            list.addAll(toList(node.getLeft()));
            list.add(node.getValue());
            list.addAll(toList(node.getRight()));
        }
        return list;
    }

    public static StringTree createTree(BiFunction<String, String, Integer> compareFunc) {
        Comparator<String> comparator = compareFunc::apply;
        return new StringTree(comparator);
    }

    private static class StringNode {
        private final String value;
        private StringNode left;
        private StringNode right;

        StringNode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public StringNode getLeft() {
            return left;
        }

        public void setLeft(StringNode left) {
            this.left = left;
        }

        public StringNode getRight() {
            return right;
        }

        public void setRight(StringNode right) {
            this.right = right;
        }
    }
}
