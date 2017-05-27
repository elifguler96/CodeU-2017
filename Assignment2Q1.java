import java.util.LinkedList;
import java.util.List;

public class Assignment2Q1 {
	public static void main(String[] args) {
		BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1, null, null);
		BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(5, null, null);
		BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(3, n1, n2);
		BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(14, null, null);
		BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(9, n3, n4);
		BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(19, null, null);
		BinaryTreeNode<Integer> n7 = new BinaryTreeNode<>(18, null, n6);
		BinaryTreeNode<Integer> n8 = new BinaryTreeNode<>(16, n5, n7);

		printAncestors(5, n8);
	}

	/**
	 * Prints the ancestors of a node in a binary tree
	 * 
	 * @param key
	 *            key of the node whose ancestors are going to be printed
	 * @param root
	 *            root of the binary tree
	 * @throws IllegalArgumentException
	 *             if key is not present in the tree
	 */
	public static <T> void printAncestors(T key, BinaryTreeNode<T> root) throws IllegalArgumentException {
		List<T> list = findAncestors(key, root);

		if (list == null) {
			throw new IllegalArgumentException();
		}

		for (T el : list) {
			System.out.print(el + " ");
		}
	}

	/**
	 * Helper method of printAncestors
	 * 
	 * @param key
	 *            key of the node whose ancestors are going to be printed
	 * @param root
	 *            root of the binary tree
	 * @return list of ancestors of the node
	 */
	private static <T> List<T> findAncestors(T key, BinaryTreeNode<T> root) {
		if (root == null) {
			return null;
		}

		// the point at which the key is found
		// constructs the empty ancestor list
		if (root.getData().equals(key)) {
			return new LinkedList<T>();
		}

		List<T> ancestorList = findAncestors(key, root.getLeft());
		// if key is on the left of the root & the key is found
		if (ancestorList != null) {
			ancestorList.add(root.getData());
		} else {
			ancestorList = findAncestors(key, root.getRight());

			// if key is on the right of the root & the key is found
			if (ancestorList != null) {
				ancestorList.add(root.getData());
			} else {
				return null;
			}
		}

		return ancestorList;
	}
}

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
}
