

public class KMP {
	
	public static int [] next;
	
	public static int kmpSearch(char[] p, char[] t){
		int i;
		int j;
		int m = p.length;
		int n = t.length;
		initnext(p);
		for(i=0, j=0; (j<m) && (i<n); i++, j++){
			while((j>=0) && (t[i] != p[j])){
				j = next[j];
			}			
		}
		if(j == m){
			return i-m;
		}
		else{
			return i;
		}
	}
	
	public static void initnext(char[] p){
		int i;
		int j; 
		next = new int[p.length+1];
		int m = p.length;
		next[0] = -1;
		for(i=0, j=-1; i<m; i++, j++, next[i]=j){
			while((j>=0) && (p[i] != p[j])){
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

	public static void main(String[] args) {
		String text = "Mr and Mrs Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal,"
				+ "thank you very much." + "And here Dursley apears again!";
		System.out.println(text);
		
		char[] textArray = text.toCharArray();
		print(textArray);
		System.out.println(textArray[11]);
		
		String pattern = "Dursley";
		char[] patternArray = pattern.toCharArray();
		System.out.println("The pattern '" + pattern + "' starts at position " + kmpSearch(patternArray, textArray) + ".");
		

	}

}
