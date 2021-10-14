import java.awt.Point;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class main {

	public static void affiche_resultat(ArrayList<Point> hull) {
		ArrayList<Point> newList = (ArrayList<Point>) hull.stream()
                .distinct()
                .collect(Collectors.toList());
		System.out.println("Les points de l'envoloppe convexe sont:");
		for(int i=0;i<hull.size();i++) {
			System.out.format(Locale.FRANCE,"{%d:%d}\n",hull.get(i).x,hull.get(i).y);
		}
		System.out.println("sa marche:");
		for(int i=0;i<newList.size();i++) {
			System.out.format(Locale.FRANCE,"{%d:%d}\n",newList.get(i).x,newList.get(i).y);
		}
	}
	
	public static void main(String[] args){
		ArrayList <Point> points = new ArrayList<Point>();
		points.add(new Point(0, 3));
		points.add(new Point(1, 1));
		points.add(new Point(2, 2));
		points.add(new Point(4, 4));
		points.add(new Point(1, 2));
		points.add(new Point(0, 0));
		points.add(new Point(3, 1));
		points.add(new Point(3, 3));
		points.add(new Point(5, 3));
		points.add(new Point(2, 3));
		points.add(new Point(6, 3));
		QuickHull conver = new QuickHull(points);
		int n = points.size();
		System.out.format("le nombre des points dans l'espace sont %d\n",n);
		if(n<=3) {
			affiche_resultat(points);
		}else {
		    int min_x = 0, max_x = 0;
		    for (int i=1; i<n; i++){
		        if (points.get(i).x <  points.get(min_x).x)
		            min_x = i;
		        if (points.get(i).x > points.get(max_x).x)
		            max_x = i;
		    }
		    System.out.format("le minimun est %s\n",points.get(min_x).toString());
		    System.out.format("le maximun est %s\n",points.get(max_x).toString());
		    conver.hull.add(points.get(min_x));
		    conver.hull.add(points.get(max_x));
		    conver.quickHull(points.get(min_x),points.get(max_x),1);
		    conver.quickHull(points.get(min_x),points.get(max_x),-1);
		    affiche_resultat(conver.hull);
		}	
		}
	}
