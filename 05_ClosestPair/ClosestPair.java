import java.awt.Point;
import java.util.*;

public class ClosestPair {

		//Comparators um die Punkte einmal nach x und einmal nach y zu sortieren
		public static Comparator<Point> compX = (p1, p2) ->{
			if(p1.x < p2.x){
				return -1;
			}
			else if(p1.x > p2.x){
				return 1;
			}
			else{
				return 0;
			}
		};

		public static Comparator<Point> compY = (p1, p2) ->{
			if(p1.y < p2.y){
				return -1;
			}
			else if(p1.y > p2.y){
				return 1;
			}
			else{
				return 0;
			}
		};

		public static double distance(Point p, Point q){
			//berechnet die Distanz zwischen zwei Punkten
			double a = p.x - q.x;
			double b = p.y - q.y;
			return Math.sqrt((a*a)+(b*b));
		}

		public static double bruteForceClosestPair(ArrayList<Point> byX){
			//berechnet minimales Distanz aller gegebenen Punkte
			double minDist = Double.POSITIVE_INFINITY;
			for(Point p : byX){
				for(Point q : byX){
					if((p.x != q.x) && (p.y != q.y) && (distance(p,q) < minDist)){
						minDist = distance(p,q);
					}
				}
			}
			return minDist;
		}


		public static double closestPair(ArrayList<Point> byX, ArrayList<Point> byY, int n){
			//berechnet minimale Distanz

			if(n <= 3){
				return bruteForceClosestPair(byX);
			}

			//Mitte bei m
			int m = ((int) Math.ceil(n/2)) -1;

			//Aufteilen in Linke und Rechte Seite, einmal nach x, einmal nach y sortiert
			ArrayList<Point> xL = new ArrayList<Point>();
			ArrayList<Point> xR = new ArrayList<Point>();
			ArrayList<Point> yL = new ArrayList<Point>();
			ArrayList<Point> yR = new ArrayList<Point>();

			for(int i = 0; i <= m; i++){
				xL.add(byX.get(i));
				yL.add(byY.get(i));
			}

			for(int i = m+1; i < n; i++){
				xR.add(byX.get(i));
				yR.add(byY.get(i));
			}


			//Rekursion, um minimalen Abstand Rechts und Links zu finden
			double deltaLeft = closestPair(xL, yL ,((int) Math.ceil(n/2)));
			double deltaRight = closestPair(xR, yR, ((int) Math.floor(n/2)));

			double delta = Math.min(deltaLeft, deltaRight);

			/*Mittelpunkt mP, durch diesen Punkt wird sozusagen die Trennlinie
			*parallel zur y-Achse gezogen
			*/
			Point mP = byX.get(m);

			/*Jetzt eine ArrayList für den Bereich, in dem alle Punkte liegen,
			 * deren x Abstand zur Mittellinie kleiner ist als delta
			 */
			ArrayList<Point> deltaStripe = new ArrayList<Point>();
			for(Point p : byY){
				if(Math.abs(p.x - mP.x) < delta){
					deltaStripe.add(p);
				}
			}

			double minDist = delta;
			for(int i = 0; i < deltaStripe.size()-2; i++){
				/*durch diese letzten "übrigen" Punkte im Streifen um die Mitellinie iterieren,
				 * alle Distanzen berechnen (sind höchstens 24 (lt. (Wikipedia)) und die kleinste davon ist dann
				 * die gesuchte kleinste Distanz.
				 */
				for(int j = i+1; j <= deltaStripe.size()-1; j++){
					double d = distance(deltaStripe.get(i), deltaStripe.get(j));
					if(d < delta){
						minDist = Math.min(minDist, d);
					}
				}
			}
			return minDist;
		}


		public static void main(String[] args) {

			/*Erst Punkte in ArrayList, damit contains funktioniert,
			 * dann in Array um nach x und y zu sortieren, dann wieder in ArrayList,
			 * damit die Funktion damit arbeiten kann. Das geht mit Sicherheit eleganter,
			 * aber es funktioniert endlich, hey! \o/
			 */

			ArrayList<Integer> X = new ArrayList<Integer>();
			ArrayList<Integer> Y = new ArrayList<Integer>();

			Random rand = new Random();

			while(X.size() < 100){
				int x = rand.nextInt(200) +1;
				if(!X.contains(x)){
					X.add(x);
				}
			}

			while(Y.size() < 100){
				int y = rand.nextInt(200) +1;
				if(!Y.contains(y)){
					Y.add(y);
				}
			}

			Point[] byX = new Point[100];
			Point[] byY = new Point[100];

			for(int i = 0; i < 100; i++){
				Point b = new Point(X.get(i), Y.get(i));
				byX[i] = b;
				byY[i]= b;
			}
			Arrays.sort(byX,compX);
			Arrays.sort(byY, compY);

			ArrayList<Point> setX = new ArrayList<Point>();
			ArrayList<Point> setY = new ArrayList<Point>();

			for(int i = 0; i < byX.length; i++){
				setX.add(byX[i]);
				setY.add(byY[i]);
			}

			System.out.println("Die kürzeste Distanz ist: " + closestPair(setX, setY, 100));
		}

	}
