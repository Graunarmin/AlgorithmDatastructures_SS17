
public class List <T>{

	public Node <T> front;
	public Node <T> Knoten;

	public List(){
		front = null;
	}

	public boolean isEmpty(){
		return front == null;
	}

	public void printList(){
		for(Node <T> i = front; i != null; i = i.next){
			System.out.println(i.toString());
		}
	}

	public void add(T content){
		Node <T> n = Knoten;
		if(isEmpty()){
			front = new Node<T>(null, content);
		}
		else{
			Knoten = new Node<T>(null, content);
			if(front.next == null){
				front.setNext(Knoten);
			}
			else{
				n.setNext(Knoten);
			}
		}
	}

	public T getFirst(){
		return front.getData();
	}

	public T getLast(){
		Knoten = front;
		while(Knoten.next != null){
			Knoten = Knoten.next;
		}
		return Knoten.getData();
	}

	public void deleteFirst(){
		front = front.next;
	}

	public void deleteLast(){
		  if(front.next == null){
			  front = null;
		  }
		  else{
			  for(Knoten = this.front; Knoten.next.next != null; Knoten = Knoten.next){
			  }
			  Knoten.next = null;
		  }
	  }


	public void delete(T content){
		if((front != null) && front.getData().equals(content)){
			front = front.next;
		}
		else if (front != null) {
			Knoten = front;
			while(Knoten.next != null){
				Node<T> n = Knoten.next;
				if (n.getData().equals(content)) {
					Knoten.setNext(n.next);
					return;
				}
				else{
					Knoten = Knoten.next;
				}
			}
			if((Knoten.next == null) && (Knoten.getData().equals(content))){
				System.out.println("Ich gehe hier rein!");
				Knoten = null;
			}
			System.out.println("Sorry, the list does not contain the given data.");
			return;
		}
		else{
			System.out.println("Sorry, this list seems to be empty.");
		}
	}

	public void clear(){
		front = null;
	}

	public int size(){
		if(front != null){
			Knoten = front;
			int counter = 0;
			for(int i = 0; Knoten != null; ++i){
				Knoten = Knoten.next;
				counter++;
			}
			return counter;
		}
		else return 0;
	}

	public static void main(String[] args) {

		List <Integer> zettel = new List<Integer>();

		zettel.add(2);
		zettel.add(5);
		zettel.add(67);
		zettel.add(44);
		zettel.add(3);

		zettel.printList();

		System.out.println("Erste Zahl ist: " + zettel.getFirst());
		System.out.println("Letzte Zahl ist: " + zettel.getLast());

		zettel.deleteFirst();
		zettel.deleteLast();
		zettel.delete(44);

		zettel.printList();
		System.out.println("Größe der Liste ist: " + zettel.size());

		zettel.clear();
		System.out.println(zettel.size());

	}

}
