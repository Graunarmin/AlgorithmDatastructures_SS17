
public class Stack<T> extends List<T>{

  public Node <T> top;
  public Node <T> iterator;

  public Stack(){
    top = null;
  }

  public boolean isEmpty(){
		return top == null;
	}

  public void printStack(){
		for(Node <T> i = top; i != null; i = i.next){
			System.out.println(i.toString());
		}
	}

  public String returnStack(){
	  String stack = "";
	  for(Node <T> i = top; i != null; i = i.next){
		  stack += i.toString() + " ";
	  }
	  return stack;
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

  public T getLast(){
	  iterator = top;
	  while(iterator.next != null){
		  iterator = iterator.next;
	  }
	  return iterator.getData();
  }

  public void deleteLast(){
	  if(top.next == null){
		  top = null;
	  }
	  else{
		  for(iterator = this.top; iterator.next.next != null; iterator = iterator.next){
		  }
		  iterator.next = null;
	  }
  }

  public T pop(){
	  T n = getLast();
	  deleteLast();
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

	  Stack<Integer> myStack = new Stack<Integer>();
	  Stack<Character> oStack = new Stack<Character>();

	  oStack.push('+');
	  oStack.push('/');
	  oStack.printStack();

	  myStack.push(5);
	  myStack.push(4);
	  myStack.push(3);

	  myStack.printStack();
	  System.out.println();

	  myStack.pop();
	  myStack.pop();
	  System.out.println(myStack.getLast());
	  myStack.pop();
	  myStack.printStack();

  }
}
