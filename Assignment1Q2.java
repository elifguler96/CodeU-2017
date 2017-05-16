
public class Assignment1Q2 {
	public static void main(String[] args) {
		Node<Integer> n1 = new Node<>(7);
		Node<Integer> n2 = new Node<>(6, n1);
		Node<Integer> n3 = new Node<>(5, n2);
		Node<Integer> n4 = new Node<>(4, n3);
		Node<Integer> n5 = new Node<>(3, n4);
		Node<Integer> n6 = new Node<>(2, n5);
		Node<Integer> n7 = new Node<>(1, n6);

		System.out.println(kthToLast(n7, 5));
	}

	/**
	 * Finds kth to last item in a singly linkedlist.
	 * 
	 * @param list
	 *            Head node of the singly linkedlist
	 * @param k
	 *            Index from last of the node to be returned
	 * @return kth to last element
	 * @throws IllegalArgumentException
	 *             if k is out of valid range
	 * @throws NullPointerException
	 *             if list is null
	 */
	public static Integer kthToLast(Node<Integer> list, int k) throws IllegalArgumentException, NullPointerException {
		if (list == null) {
			throw new NullPointerException();
		}

		if (k < 0) {
			throw new IllegalArgumentException();
		}

		// pointers for keeping track of nodes
		Node<Integer> p1 = list;
		Node<Integer> p2 = p1;

		// gets p1 k steps ahead of p2
		for (int i = 0; i < k; i++) {
			p1 = p1.getNext();

			if (p1 == null) {
				throw new IllegalArgumentException();
			}
		}

		// when p1 reaches the end of the list, p2 is at the kth to last
		// node of the list
		while (p1.getNext() != null) {
			p1 = p1.getNext();
			p2 = p2.getNext();
		}

		return p2.getData();
	}
}

// Node class for the singly linked list
class Node<T> {
	private T data;
	private Node<T> next;

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, Node<T> next) {
		this(data);
		this.next = next;
	}

	public Node<T> getNext() {
		return next;
	}

	public Node<T> setNext(Node<T> next) {
		this.next = next;
		return this;
	}

	public T getData() {
		return data;
	}

	public Node<T> setData(T data) {
		this.data = data;
		return this;
	}
}
