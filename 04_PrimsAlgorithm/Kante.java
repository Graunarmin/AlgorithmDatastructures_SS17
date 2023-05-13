
public class Kante implements Comparable<Kante>{
	//Class Kante for Prims Algorithm
	
	public int start;
	public int end;
	public int weight;
	
	public Kante(int startKnoten, int endKnoten, int w){
		this.start = startKnoten;
		this.end = endKnoten;
		this.weight = w;
	}
	
	public Kante(Kante k){
		this.start = k.start;
		this.end = k.end;
		this.weight = k.weight;
	}
	
	public void setStart(int startKnoten){
		this.start = startKnoten;
	}
	
	public void setEnd(int endKnoten){
		this.end = endKnoten;
	}
	
	public void setWeight(int w){
		this.weight = w;
	}
	
	public int getStart(){
		return this.start;
	}
	
	public int getEnd(){
		return this.end;
	}
	
	public int getWeight(){
		return this.weight;
	}
	
	public String toString(){
		return weight + " ";
	}
	
	@Override
	public int compareTo(Kante k){
		if (k.getWeight() < this.getWeight()){
			return 1;
		}
		else if(k.getWeight() > this.getWeight()){
			return -1;
		}
		else{ //sonst sind sie gleich
			return 0;
		}
	}
		
	


}
