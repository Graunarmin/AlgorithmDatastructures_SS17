import java.util.Arrays;
import java.util.PriorityQueue;

public class Prim {

	public static boolean [] visited;	//markieren der besuchten Knoten
	public static int[][]mst; 			//leere Matrix für MST
	public static PriorityQueue<Kante> weights = new PriorityQueue<Kante>(); //Queue für min. Gewicht
	public static int counter;
	public static int s = 0;			//Zähler für Spalten der Matrix
	public static int Groesse = 12; 	//Knotenanzahl
	
	
	public static void prim(int[][]graph){
		
		counter = 0;
		visited = new boolean [Groesse];
		mst = new int[Groesse][Groesse];
		Arrays.fill(visited, false); 	//alle Knoten als "nicht besucht" markieren		
		
		while(counter < Groesse-1){ 	//MST hat (Knotenanzahl-1) Kanten				
			
			/*falls Knoten noch nicht besucht wurde: 
			*als besucht markieren und alle Kanten in die PQ schreiben*/
			if(visited[s] == false){ 
				visited[s] = true; 					
				
				for(int z = 0; z < Groesse; z++){
					if(graph[z][s] != 0){ 	
						weights.add(new Kante(s, z, graph[z][s]));
					}
				}	
				
				Kante min = weights.poll();
				
				/*falls der Endknoten dieser Kante noch nicht besucht wurde:
				 * zum MST hinzufügen
				 */
				if(visited[min.getEnd()] == false){
					mst[min.getStart()][min.getEnd()] = min.getWeight();
					mst[min.getEnd()][min.getStart()] = min.getWeight();
				}
				s = min.getEnd();
				counter ++;
			}
			
			//sonst mit der nächst kleineren Kante weitermachen
			else{
				Kante min = weights.poll();
					
				if(visited[min.getEnd()] == false){
					mst[min.getStart()][min.getEnd()] = min.getWeight();
					mst[min.getEnd()][min.getStart()] = min.getWeight();
				}
				s = min.getEnd();		
			}
		}
	}
	
	public static void printMatrix(int[][]matrix, int n){
		for(int z = 0; z < n; z++){
			for(int s = 0; s < n; s++){
				System.out.print(matrix[z][s] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		int[][] matrix = new int [][]{{0,4,0,0,0,10,0,0,0,0,0,0},
			  {4,0,2,0,0,0,0,0,0,0,0,0},
			  {0,2,0,0,7,0,5,0,0,0,0,0},
			  {0,0,0,0,1,0,8,5,0,0,0,0},
			  {0,0,7,1,0,0,2,0,0,0,0,5},
			  {10,0,0,0,0,0,2,10,0,0,0,0},
			  {0,0,5,8,2,2,0,0,0,0,0,0},
			  {0,0,0,5,0,10,0,0,3,0,0,0},
			  {0,0,0,0,0,0,0,3,0,7,0,0},
			  {0,0,0,0,0,0,0,0,7,0,1,0},
			  {0,0,0,0,0,0,0,0,0,1,0,0},
			  {0,0,0,0,5,0,0,0,0,0,0,0}};
			  
		prim(matrix);
		System.out.println("Here is the adjacency matrix of the minimal spanning tree: ");
		System.out.println();
		printMatrix(mst,12);
		
	}
	
	
}
