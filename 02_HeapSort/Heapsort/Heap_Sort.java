import java.util.ArrayList;
import java.util.Scanner;

public class Heap_Sort {

	public static ArrayList<Character> heapsort(ArrayList <Character> list){
			int last = list.size()-1;
			makeHeap(list, last);

			int i = last;
			while(i > 0){
				/*tausche das erste Elemente(das größte!)nach hinten
				*und mache die "range" des Heap um 1 kleiner*/
				swap(list, 0, last);
				last--;

				/*jetzt steht das letzte Element des Heaps vorne,
				*da muss aber das zweitgrößte Element hin,
				*also baue ich aus dem Rest wieder einen Heap*/
				makeHeap(list, last);
				i--;
			}
			return list;
			/*dann sind alle Elemente "nach hinten" getauscht worden,
			*dass größte ganz hinten, davor das nächst kleinere usw*/
		}

	public static void makeHeap(ArrayList <Character> list, int last){
			for (int i = last/2; i >= 0; i--){
				int parent = i; //i ist parent
				int left = 2*i; //Kindknoten links
				int right = left + 1; //Kindknoten rechts

				if((right <= last) && (list.get(right) > list.get(parent))){
					parent = right;
				}
				if((left <= last) && (list.get(left) > list.get(parent))){
					parent = left;
				}
				if(i != parent){
					//wenn i nicht der größte war: tauschen, um Heap zu erzeugen
					swap(list, i, parent);
					makeHeap(list, i);
				}
			}
		}

	public static void swap(ArrayList <Character> list, int i, int j){
		char temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static void main(String[] args) {

		ArrayList <Character> myList = new ArrayList<Character>();
		myList.add('c');
		myList.add('e');
		myList.add('f');
		myList.add('a');
		myList.add('b');
		myList.add('d');
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
