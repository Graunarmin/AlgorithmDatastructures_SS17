
public class Node <T> {
	
	public Node <T> next;
	public T data;
	
	public Node(Node <T> nextNode, T data){
		this.next = nextNode;
		this.data = data;
	}
	
	public void setNext(Node <T> nextNode){
		this.next = nextNode;
	}
	
	public Node <T> getNext(){
		return this.next;
	}
	
	public T getData(){
		return this.data;
	}
	
	public String toString(){
		return data + " ";
	}

}
