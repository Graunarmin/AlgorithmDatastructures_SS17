
public class Queue<T> {

	public Node <T> top;
	public Node <T> iterator;

	public Queue(){
		top = null;
	}

	public boolean isEmpty(){
		return top == null;
		}

	public void printQueue(){
		for(Node <T> i = top; i != null; i = i.next){
			System.out.print(i.toString());
		}
	}

	public String returnQueue(){
		  String queue = "";
		  for(Node <T> i = top; i != null; i = i.next){
			  queue += i.toString() + " ";
		  }
		  return queue;
	  }

	public void push(T content){
		Node <T> n = iterator;
		if(isEmpty()){
			top = new Node<T>(null, content);
		}
		else{
			iterator = new Node<T>(null, content);
			if(top.next == null){
				top.setNext(iterator);
			}
			else{
				n.setNext(iterator);
			}
		}
	}

	public T getFirst(){
		return top.getData();
	}

	public void deleteFirst(){
		top = top.next;
	}

	public T pop(){
		T n = getFirst();
		deleteFirst();
		return n;
	}

	public void clear(){
		top = null;
	}

	public int size(){
		if(top != null){
			iterator = top;
			int counter = 0;
			for(int i = 0; iterator != null; ++i){
				iterator = iterator.next;
				counter++;
			}
			return counter;
		}
		else return 0;
	}


	public static void main(String[] args) {
		Queue <Integer> myQueue = new Queue <Integer>();

		myQueue.push(5);
		myQueue.push(4);
		myQueue.push(3);

		myQueue.printQueue();
		System.out.println();

		myQueue.pop();
		myQueue.printQueue();

	}

}
