public class Assignment2Q2 {
    /**
     * Shows the common ancestor of node1 and node2 with root being the root of the tree.
     * Created for test purposes.
     *
     * @param node1 first node
     * @param node2 second node
     * @param root  root of the tree
     * @throws IllegalArgumentException if the tree doesn't contain one or both of the nodes or root is null
     */
    public static <T> void showCommonAncestor(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2, BinaryTreeNode<T> root) throws IllegalArgumentException {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        try {
            System.out.println(root.findCommonAncestor(node1, node2).toString());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
