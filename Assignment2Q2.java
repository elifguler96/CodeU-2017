
public class Assignment2Q2 {
	public static void main(String[] args) {
		// construct the tree
		BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1, null, null);
		BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(5, null, null);
		BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(3, n1, n2);
		BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(14, null, null);
		BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(9, n3, n4);
		BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(19, null, null);
		BinaryTreeNode<Integer> n7 = new BinaryTreeNode<>(18, null, n6);
		BinaryTreeNode<Integer> n8 = new BinaryTreeNode<>(16, n5, n7);
		BinaryTreeNode<Integer> n9 = new BinaryTreeNode<>(186, null, null);

		System.out.println(findCommonAncestors(n9, n1, n8).getData());
	}

	/**
	 * Finds lowest common ancestor of node1 and node2
	 * 
	 * @param node1
	 *            first node
	 * @param node2
	 *            second node
	 * @param root
	 *            root of the tree
	 * @return lowest common ancestor of node1 and node2
	 * @throws IllegalArgumentException
	 *             if one or both of the nodes are not present in the tree
	 */
	public static <T> BinaryTreeNode<T> findCommonAncestors(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2,
			BinaryTreeNode<T> root) throws IllegalArgumentException {
		if (node1 == null || node2 == null || root == null) {
			throw new IllegalArgumentException();
		}

		BinaryTreeNode<T> node = findCommonAncestorsHelper(node1, node2, root);

		if (node == null) {
			throw new IllegalArgumentException();
		}

		// if node1 is the common ancestor, check if node2 is in the tree which
		// has root node1. Also handles the case if node1 and node2 are the
		// same.
		if (node.getData().equals(node1.getData())) {
			if (node1.getData().equals(node2.getData()) || contains(node2, node)) {
				return node;
			}

			throw new IllegalArgumentException();
		}

		// if node2 is the common ancestor, check if node1 is in the tree which
		// has root node2.
		if (node.getData().equals(node2.getData())) {
			if (contains(node1, node)) {
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
	 * @param node1
	 *            first node
	 * @param node2
	 *            second node
	 * @param root
	 *            root of the tree
	 * @return common ancestor of node1 and node2
	 */
	private static <T> BinaryTreeNode<T> findCommonAncestorsHelper(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2,
			BinaryTreeNode<T> root) {
		if (root == null) {
			return null;
		}

		if (root.getData().equals(node1.getData()) || root.getData().equals(node2.getData())) {
			return root;
		}

		// common ancestor of left subtree
		BinaryTreeNode<T> leftCA = findCommonAncestorsHelper(node1, node2, root.getLeft());
		// common ancestor of right subtree
		BinaryTreeNode<T> rightCA = findCommonAncestorsHelper(node1, node2, root.getRight());

		// one of the nodes is on the left subtree, other is on the right
		// subtree
		if (leftCA != null && rightCA != null) {
			return root;
		}

		// both are on the left sub tree
		if (leftCA != null) {
			return leftCA;
		}

		// both are on the right subtree
		return rightCA;
	}

	/**
	 * Checks if node is present in the tree whose root is root
	 * 
	 * @param node
	 *            node to be checked
	 * @param root
	 *            root of the tree
	 * @return true if present, false otherwise
	 */
	private static <T> boolean contains(BinaryTreeNode<T> node, BinaryTreeNode<T> root) {
		if (node == null || root == null) {
			return false;
		}

		if (root.getData() == node.getData()) {
			return true;
		}

		return contains(node, root.getLeft()) || contains(node, root.getRight());
	}
}
