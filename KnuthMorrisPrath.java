import java.util.ArrayList;

public class KnuthMorrisPrath {

public static int [] next;
public static ArrayList<Integer> positions = new ArrayList<Integer>();
	
	public static ArrayList<Integer> kmpSearch(char[] p, char[] t){
		int i;
		int j;
		int m = p.length;
		int n = t.length;
		initnext(p);
		i=0;
		while(i<n){
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
	
	public static void print(char[] x){
		for (int i = 0; i< x.length; i++){
			System.out.print(x[i]);
		}
		System.out.println();
	}
	
	public static void print(int[] x){
		for (int i = 0; i< x.length; i++){
			System.out.print(x[i] + ",");
		}
		System.out.println();
	}
	
	public static void kmp(char[] p, char[] t){
		kmpSearch(p, t);
		String pattern = new String(p);
		if (positions.size() == 0){
			System.out.println("Sorry, but '" + pattern + "' can not be found in the text.");
		}
		else if(positions.size() == 1){
			System.out.println("The pattern '" + pattern + "' can be found starting at position " + positions.get(0) + ".");
		}
		else{
			System.out.print("The pattern '" + pattern + "' can be found starting at positions ");
			for(int i = 0; i < positions.size() -1; i++){
				System.out.print(positions.get(i) + " and ");
			}
			System.out.println(positions.get(positions.size()-1) + ".");
		}
		positions.clear();
	}
	

	public static void main(String[] args) {
		
		String abc = "ababcacbabc";
		System.out.println("This is the text: " + abc);
		System.out.println();
		
		char[] abcArray = abc.toCharArray();
		
		String pattern = "abc";
		char[] patternArray = pattern.toCharArray();
		
		kmp(patternArray, abcArray);
		System.out.println();
		System.out.println();
		
		String text = "Mr and Mrs Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal,"
				+ "thank you very much. "
				+ "\nThey were the last people you'd expect to be involved in anything strange or mysterious, "
				+ "because they just didn't hold with such nonsense.";
		System.out.println("This is the text: " + text);
		System.out.println();
		
		char[] textArray = text.toCharArray();
		
		String pattern2 = "Dursley";
		char[] pattern2Array = pattern2.toCharArray();
//		System.out.println(textArray[11]);
//		System.out.println(textArray[12]);
		kmp(pattern2Array, textArray);
		
		String pattern3 = "were";
		char[] pattern3Array = pattern3.toCharArray();
//		System.out.println(textArray[127]);
//		System.out.println(textArray[128]);
//		System.out.println(textArray[129]);
//		System.out.println(textArray[130]);
		kmp(pattern3Array, textArray);
		

	}

}
