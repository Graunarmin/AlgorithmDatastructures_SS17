import java.awt.Point;
import java.util.*;

public class ClosestPair40Points {
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

	public static double bruteForceClosestPair(Point[] byX){
		//berechnet minimales Distanz aller Punkte aus dem geg. Array
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


	public static double closestPair(Point[] byX, Point[] byY, int n){
		//berechnet minimale Distanz

		if(n <= 3){
			return bruteForceClosestPair(byX);
		}

		//Mitte bei m
		int m = ((int) Math.ceil(n/2)) -1;

		//ArrayLists, da es dynamisch sein musste, geht bestimmt auch eleganter /o\
		ArrayList<Point> xL = new ArrayList<Point>();
		ArrayList<Point> xR = new ArrayList<Point>();
		ArrayList<Point> yL = new ArrayList<Point>();
		ArrayList<Point> yR = new ArrayList<Point>();

		for(int i = 0; i <= m; i++){
			xL.add(byX[i]);
			yL.add(byY[i]);
		}

		for(int i = m+1; i < n; i++){
			xR.add(byX[i]);
			yR.add(byY[i]);
		}

		//Jetzt alles in Arrays packen, damit auf Positionen zugegriffen werden kann
		Point[] xLeft = new Point[xL.size()];
		Point[] xRight = new Point[xR.size()];
		Point[] yLeft = new Point[yL.size()];
		Point[] yRight = new Point[yR.size()];

		int ixL = 0;
		for(Point p : xL){
			//System.out.println("Links füllen.");
			xLeft[ixL] = p;
			ixL++;
		}

		int iyL = 0;
		for(Point p : yL){
			yLeft[iyL] = p;
			iyL++;
		}

		int ixR = 0;
		for(Point p : xR){
			//System.out.println("Rechts füllen.");
			xRight[ixR] = p;
			ixR++;
		}

		int iyR = 0;
		for(Point p : xL){
			yRight[iyR] = p;
			iyR++;
		}

		//Rekursion um minimalen Abstand Rechts und Links zu finden
		double deltaLeft = closestPair(xLeft, yLeft ,((int) Math.ceil(n/2)));
		double deltaRight = closestPair(xRight, yRight, ((int) Math.floor(n/2)));

		//Delta gesamt ist Minimum der oben ermittelten
		double delta = Math.min(deltaLeft, deltaRight);

		//Mittelpunkt mP
		Point mP = byX[m];


		/*Jetzt eine ArrayList (-> dynamisch, später Array) für den Streifen, in dem alle Punkte liegen,
		 * deren x Abstand zur Mittellinie kleiner ist als delta
		 */
		ArrayList<Point> deltaStripe = new ArrayList<Point>();
		for(Point p : byY){
			if(Math.abs(p.x - mP.x) < delta){
				deltaStripe.add(p);
			}
		}

		Point[] stripe = new Point[deltaStripe.size()];
		int iyS = 0;
		for(Point p : deltaStripe){
			stripe[iyS] = p;
			iyS++;
		}

		double minDist = delta;
		for(int i = 0; i < stripe.length-2; i++){
			/*durch diese letzten "übrigen" Punkte im Streifen um die Mitellinie iterieren,
			 * alle Distanzen berechnen (sind höchstens 24 (lt. (wikipedia)) und die kleinste davon ist dann
			 * die gesuchte kleinste Distanz.
			 */

			for(int j = i+1; j <= stripe.length -1; j++){
				double d = distance(stripe[i], stripe[j]);
				if(d < delta){
					minDist = Math.min(minDist, d);
				}
			}
		}
		return minDist;
	}


	public static void main(String[] args) {

		ArrayList<Integer> X = new ArrayList<Integer>();
		ArrayList<Integer> Y = new ArrayList<Integer>();

		Random rand = new Random();

		while(X.size() < 40){
			int x = rand.nextInt(200) +1;
			if(!X.contains(x)){
				X.add(x);
			}
		}

		while(Y.size() < 40){
			int y = rand.nextInt(200) +1;
			if(!Y.contains(y)){
				Y.add(y);
			}
		}

		Point[] byX = new Point[40];
		Point[] byY = new Point[40];

		for(int i = 0; i < 40; i++){
			Point b = new Point(X.get(i), Y.get(i));
			byX[i] = b;
			byY[i]= b;
		}
		Arrays.sort(byX,compX);
		Arrays.sort(byY, compY);

		X.clear();
		Y.clear();

		System.out.println("Die kürzeste Distanz ist: " + closestPair(byX, byY, 40));
	}

}
