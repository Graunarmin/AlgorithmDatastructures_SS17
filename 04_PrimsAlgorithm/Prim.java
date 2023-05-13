
import java.util.Arrays;
import java.util.PriorityQueue;

public class Prim {

	public static boolean[] visited;			//markieren der besuchten Knoten
	public static int[][] mst; 					//leere Matrix für MST
	public static PriorityQueue<Kante> weights = new PriorityQueue<Kante>(); //zum Bestimmen der kleinsten Kante
	public static int s = 0;					//Zähler für Spalten der Matrix
	public static int Groesse = 12; 			//Knotenanzahl


	public static void prim(int[][]graph){

		int counter = 0;
		visited = new boolean [Groesse];
		Arrays.fill(visited, false); 			//alle Knoten als "nicht besucht" markieren
		mst = new int[Groesse][Groesse];

		while(counter < Groesse-1){ 			//MST hat (Knotenanzahl-1) Kanten

			/*falls Knoten noch nicht besucht wurde:
			*alle Kanten in die PQ schreiben und den Knoten als besucht markieren
			*/
			if(visited[s] == false){

				for(int z = 0; z < Groesse; z++){
					if(graph[z][s] != 0){
						weights.add(new Kante(s, z, graph[z][s]));
					}
				}
				visited[s] = true;

				//kleinste Kante raussuchen
				Kante min = weights.poll();

				/*falls der Endknoten dieser Kante noch nicht besucht wurde:
				 * zum MST hinzufügen, s auf Endknoten setzten und Counter erhöhen
				 */
				if(visited[min.getEnd()] == false){
					mst[min.getStart()][min.getEnd()] = min.getWeight();
					mst[min.getEnd()][min.getStart()] = min.getWeight();
				}
				s = min.getEnd();
				counter ++;
			}

			/*sonst mit der nächst kleineren Kante weitermachen, hier aber Counter
			*nicht erhöhen, da die Kante ja von einem bereits besuchten Knoten ausgeht
			*/
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

		int[][] matrix = new int [][]{
			  {0,4,0,0,0,10,0,0,0,0,0,0},
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
