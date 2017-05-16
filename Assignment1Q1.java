import java.util.HashMap;
import java.util.Map;

public class Assignment1Q1 {
	public static void main(String[] args) {
		System.out.println(isPermutation("Apple", "Pabble"));
	}

	/**
	 * Checks if two strings are permutations of each other
	 * 
	 * @param s1
	 *            String1
	 * @param s2
	 *            String2
	 * @return true if s1 and s2 are permutations, false otherwise
	 */
	public static boolean isPermutation(String s1, String s2) {
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
}
