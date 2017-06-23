import java.util.LinkedList;
import java.util.List;

class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Finds the ancestors of the node with the given <code>key</code>.
     *
     * @param key key of the node whose ancestors are going to be printed
     * @return list of ancestors of the node
     */
    List<T> findAncestors(T key) {
        // the point at which the key is found
        // constructs the empty ancestor list
        if (getData().equals(key)) {
            return new LinkedList<T>();
        }

        List<T> ancestorList = null;

        if (getLeft() != null) {
            ancestorList = getLeft().findAncestors(key);
        }

        // if key is on the left of the root & the key is found
        if (ancestorList != null) {
            ancestorList.add(getData());
        } else {
            if (getRight() != null) {
                ancestorList = getRight().findAncestors(key);
            }

            // if key is on the right of the root & the key is found
            if (ancestorList != null) {
                ancestorList.add(getData());
            } else {
                return null;
            }
        }

        return ancestorList;
    }

    /**
     * Finds lowest common ancestor of node1 and node2
     *
     * @param node1 first node
     * @param node2 second node
     * @return lowest common ancestor of node1 and node2
     * @throws IllegalArgumentException if one or both of the nodes are not present in the tree
     */
    BinaryTreeNode<T> findCommonAncestor(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) throws IllegalArgumentException {
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException();
        }

        BinaryTreeNode<T> node = findCommonAncestorHelper(node1, node2);

        if (node == null) {
            throw new IllegalArgumentException();
        }

        // if node1 is the common ancestor, check if node2 is in the tree which
        // has root node1. Also handles the case if node1 and node2 are the
        // same.
        if (node.equals(node1)) {
            if (node1.equals(node2) || node1.contains(node2)) {
                return node;
            }

            throw new IllegalArgumentException();
        }

        // if node2 is the common ancestor, check if node1 is in the tree which
        // has root node2.
        if (node.equals(node2)) {
            if (node2.contains(node1)) {
                return node;
            }

            throw new IllegalArgumentException();
        }

        return node;
    }

    /**
     * Helper method of findCommonAncestors. Partially finds the common ancestor
     * of node1 and node2
     *
     * @param node1 first node
     * @param node2 second node
     * @return common ancestor of node1 and node2
     */
    private BinaryTreeNode<T> findCommonAncestorHelper(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
        if (equals(node1) || equals(node2)) {
            return this;
        }

        BinaryTreeNode<T> leftCA = null;
        BinaryTreeNode<T> rightCA = null;

        if (getLeft() != null) {
            // common ancestor of left subtree
            leftCA = getLeft().findCommonAncestorHelper(node1, node2);
        }
        if (getRight() != null) {
            // common ancestor of right subtree
            rightCA = getRight().findCommonAncestorHelper(node1, node2);
        }

        // one of the nodes is on the left subtree, other is on the right
        // subtree
        if (leftCA != null && rightCA != null) {
            return this;
        }

        // both are on the left sub tree
        if (leftCA != null) {
            return leftCA;
        }

        // both are on the right subtree or key does not exist in the tree
        return rightCA;
    }

    /**
     * Checks if node is present in the tree whose root is root
     *
     * @param node node to be checked
     * @return true if present, false otherwise
     */
    boolean contains(BinaryTreeNode<T> node) {
        if (node == null) {
            return false;
        }

        if (equals(node)) {
            return true;
        }

        boolean leftContains = false;
        boolean rightContains = false;

        if (getLeft() != null) {
            leftContains = getLeft().contains(node);
        }

        if (getRight() != null) {
            rightContains = getRight().contains(node);
        }

        return leftContains || rightContains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;

        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                '}';
    }
}
