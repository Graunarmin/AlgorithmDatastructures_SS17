import java.util.ArrayList;
import java.util.Scanner;

public class Heapsort {

	public static int size;

	public static ArrayList<Character> heapsort(ArrayList <Character> list){
			//Heap bauen
			makeHeap(list);

	    for(int i = size; i > 0; i--){
				/*tausche das erste Elemente(das größte!)nach hinten
				*und mache die "range" des Heap um 1 kleiner*/
	      swap(0, i, list);
	      size = size-1;
				/*nun steht das letzt Element vorne,
				*da muss aber jetzt das zweitgrößte Element hin,
				*also baue ich aus dem Rest wieder einen Heap*/
	      compare(list, 0);
	    }
	    return list;
	  }

	public static void makeHeap(ArrayList <Character> list){
	    size = list.size() -1;
	    for (int i = size/2; i >= 0; i--){
	    	compare(list,i);
	    }
	  }

	public static void compare(ArrayList <Character> list, int i){
	    //i ist parent
			int greatest = i; //pointer auf greatest
		  int left = 2*i; //pointer auf right child
		  int right = left + 1; //pointer auf left child

	    if(left <= size && list.get(left) > list.get(greatest)){
	      /*wenn noch innerhalb des Arrays und left größer parent
	      *greatest auf left */
	      greatest = left;
	    }
	    if(right <= size && list.get(right) > list.get(greatest)){
	    /*wenn noch innerhalb des Arrays und right größer greatest,
	    *greatest auf right */
	      greatest = right;
	    }
	    if(greatest != i){
	      //wenn i nicht der größte war: tauschen
	      swap(i, greatest, list);
	      compare(list, greatest);
	    }
	  }

	public static void swap(int i, int j, ArrayList <Character> list){
		char temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}


	public static void main(String[] args) {

		ArrayList <Character> myList = new ArrayList<Character>();
		myList.add('s');
		myList.add('a');
		myList.add('g');
		myList.add('z');
		System.out.println("The list is " + myList);
		System.out.println("And sorted it looks like this:" + heapsort(myList));
		System.out.println();


		ArrayList <Character> myOtherList = new ArrayList<Character>();
		System.out.println("Please enter 6 characters which shall be sorted (please press enter after each): ");
		int i = 0;
		while(i < 6){
			Scanner scan = new Scanner (System.in);
			char a = scan.nextLine().charAt(0);
			myOtherList.add(a);
			i++;
		}

		System.out.println(heapsort(myOtherList));

	}

}
