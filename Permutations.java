import java.util.Arrays;
import java.util.Vector;

public class Permutations {
	//main method for start the  execution of the program 
	public static void main(String[] args) {
		//declaration of string literal
		String s = "ABC";
		char[] chs = s.toCharArray(); // convert string to character array
		Arrays.sort(chs); // sort the array to get the lexicographically sorted order
		s = new String(chs);
		int size = s.length();
		Vector<String> result = new Vector<>(); //vector to store the result
		result = find_permutaion(s, 0, size - 1,result); 
		System.out.println(result);

	}
	//method for change the value
	public static String change(String s, int index1, int index2) {
		char temp;
		char[] charArray = s.toCharArray();
		temp = charArray[index1];
		charArray[index1] = charArray[index2];
		charArray[index2] = temp;
		return String.valueOf(charArray);
	}
	//method for get the Vector of String of permutaion
	private static Vector<String> find_permutaion(String s, int index, int size, Vector<String> result) {
		if (index == size) {
			result.add(s);
		}
		else {
			for (int index1 = index; index1 <= size; index1++) {
				s = change(s, index, index1);
				find_permutaion(s, index + 1, size,result);//method recursion
				s = change(s, index, index1);
			}
		}
		return result;
	}
}
