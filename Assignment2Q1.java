import java.util.List;

public class Assignment2Q1 {
    /**
     * Prints the ancestors of a node in a binary tree
     *
     * @param key  key of the node whose ancestors are going to be printed
     * @param root root of the binary tree
     * @throws IllegalArgumentException if key is not present in the tree
     */
    public static <T> void printAncestors(T key, BinaryTreeNode<T> root) throws IllegalArgumentException {
        List<T> list = root.findAncestors(key);

        if (list == null) {
            throw new IllegalArgumentException();
        }

        for (T el : list) {
            System.out.print(el + " ");
        }
    }

}

