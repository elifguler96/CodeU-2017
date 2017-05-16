import java.util.HashMap;
import java.util.Map;

public class Assignments {

	public static void main(String[] args) {
		Assignment1 assignment = new Assignment1();

		System.out.println(assignment.q1Permutation("Apple", "Pabble"));

		Node<Integer> n1 = new Node<>(7);
		Node<Integer> n2 = new Node<>(6, n1);
		Node<Integer> n3 = new Node<>(5, n2);
		Node<Integer> n4 = new Node<>(4, n3);
		Node<Integer> n5 = new Node<>(3, n4);
		Node<Integer> n6 = new Node<>(2, n5);
		Node<Integer> n7 = new Node<>(1, n6);

		System.out.println(assignment.q2KthToLast(n7, 5));
	}

	static class Assignment1 {

		/**
		 * Checks if two strings are permutations of each other
		 * 
		 * @param s1
		 *            String1
		 * @param s2
		 *            String2
		 * @return true if s1 and s2 are permutations, false otherwise
		 */
		public boolean q1Permutation(String s1, String s2) {
			if (s1 == null || s2 == null) {
				return false;
			}

			// to count A and a as the same letter
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();

			// if s1 and s2's lengths are not equal, they can't be permutations
			if (s1.length() != s2.length()) {
				return false;
			}

			Map<Character, Integer> map = new HashMap<>();

			// maps each character of s1 to its number of occurrences in s1
			for (Character c : s1.toCharArray()) {
				int occurrenceBefore = map.getOrDefault(c, 0);
				map.put(c, occurrenceBefore + 1);
			}

			for (Character c : s2.toCharArray()) {
				// if c is not present in s1, s1 and s2 are not permutations
				if (!map.containsKey(c)) {
					return false;
				}
				// decreases value of c by one whenever it is found
				map.put(c, map.get(c) - 1);

				// if value of c is less than zero, s1 and s2 are not
				// permutations
				if (map.get(c) < 0) {
					return false;
				}
			}

			return true;
		}

		/**
		 * Finds kth to last item in a singly linkedlist.
		 * 
		 * @param list
		 *            Head node of the singly linkedlist
		 * @param k
		 *            Index from last of the node to be returned
		 * @return kth to last element
		 */
		public Integer q2KthToLast(Node<Integer> list, int k) {
			if (list == null || k < 0) {
				return null;
			}

			// pointers for keeping track of nodes
			Node<Integer> p1 = list;
			Node<Integer> p2 = p1;

			// gets p1 k steps ahead of p2
			for (int i = 0; i < k; i++) {
				p1 = p1.getNext();

				if (p1 == null) {
					return null;
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
