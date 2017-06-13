//I used the lecture script for help, hope, that was ok

import java.util.ArrayList;
import java.util.Scanner;

public class KnuthMorrisPrath {

	public static int[] next;
	public static ArrayList<Integer> positions = new ArrayList<Integer>();

	public static void kmp(char[] p, char[] t){
		//calls the search and prints out the results

		kmpSearch(p, t);
		String pattern = new String(p); //easier to print this way

		if (positions.size() == 0){
			System.out.println("Sorry, but '" + pattern + "' can not be found in the text.");
		}
		else if(positions.size() == 1){
			System.out.println("The pattern '" + pattern + "' can be found starting at position " + positions.get(0) + ".");
		}
		else{
			System.out.print("The pattern '" + pattern + "' can be found " + positions.size()
			+ " times in the text, starting at positions ");
			for(int i = 0; i < positions.size() -1; i++){
				System.out.print(positions.get(i) + " and ");
			}
			System.out.println(positions.get(positions.size()-1) + ".");
		}
		positions.clear();
	}

	public static ArrayList<Integer> kmpSearch(char[] p, char[] t){
		/*searches the pattern by using the "next" Array, which is the match table for this pattern
		 * created in "initnext(p)"
		 */

		int i;
		int j;
		int m = p.length;
		int n = t.length;
		initnext(p);
		i = 0;
		while(i < n){
		for(j = 0; (j < m) && (i < n); i++, j++){
			while((j >= 0) && (t[i] != p[j])){
				j = next[j];
			}
		}
		if(j == m){
			positions.add(i-m);
		}
		}
		return positions;
	}

	public static void initnext(char[] p){
		//creates a "match table" to find out how many times the pattern can be shifted forwards in case of a mismatch

		int i;
		int j;
		next = new int[p.length+1];
		int m = p.length;
		next[0] = -1;
		for(i = 0, j = -1; i < m; i++, j++, next[i]=j){
			while((j >= 0) && (p[i] != p[j])){
				j = next[j];
			}
		}
	}

	public static void main(String[] args) {

		String text = "Mr and Mrs Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal,"
				+ "thank you very much. "
				+ "\nThey were the last people you'd expect to be involved in anything strange or mysterious, "
				+ "because they just didn't hold with such nonsense.\n"
				+ "Mr Dursley was the director of a firm called Grunnings, which made drills.\n"
				+ "(from: Harry Potter and the Philosophers Stone by J.K.Rowling, Page 1)";
		System.out.println("This is the text: " + text);
		System.out.println();

		char[] textArray = text.toCharArray();

		String pattern2 = "Dursley";
		char[] pattern2Array = pattern2.toCharArray();
		kmp(pattern2Array, textArray);
		System.out.println();

		String pattern3 = "were";
		char[] pattern3Array = pattern3.toCharArray();
		kmp(pattern3Array, textArray);
		System.out.println();

		String pattern4 = "Voldemort";
		char[] pattern4Array = pattern4.toCharArray();
		kmp(pattern4Array, textArray);
		System.out.println();


		System.out.println("Please enter the word you would like to search in the text above: ");
		Scanner pat = new Scanner (System.in);
		String pattern5 = pat.nextLine();
		char[] pattern5Array = pattern5.toCharArray();
		kmp(pattern5Array, textArray);
		System.out.println();
		System.out.println();

		System.out.println("Now enter a text to search in: ");
		Scanner scan = new Scanner (System.in);
		String text1 = scan.nextLine();
		char[] text1Array = text1.toCharArray();

		System.out.println("And now the word you want to search for: ");
		Scanner scan2 = new Scanner (System.in);
		String pattern = scan2.nextLine();
		char[] patternArray = pattern.toCharArray();
		kmp(patternArray, text1Array);

	}

}
