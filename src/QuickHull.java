import java.util.ArrayList;
import java.util.Locale;
// importer la librairie qui gere les listes
// la librarie pour gere les points dans un repere (x,y)
import java.awt.Point;


// creation de la classe responsable de l'agorithme du quickhull
public class QuickHull{
//	les donnes d'entree est une listes de pounts
	private ArrayList<Point> point = new ArrayList<Point>();
//	donnees de sortie de l'algorithme les points de l'enveloppe convexe
	public ArrayList<Point> hull = new ArrayList<Point>();
	// la taille de la liste
	private int n = 0;
	
// SearchSide la fonction qui determine de quel cote de 
//	la droite se trouve le point
//	nous permet de decide si ce point est du bon cote ou si doit encore prs en consideration
	
	public Integer searchSide(Point a,Point b,Point p){
		Integer value = (p.y - a.y)*(b.x - a.x)-(b.y-a.y)*(p.x-b.x);
		if(value>0) {
			return 1;
		}
		if(value<0) {
			return -1;
		}
		return 0;
	}
//	mesure la distance entre un point p et 
//	le segment de droite cree pas les points a et b
	
	public Integer distance(Point a,Point b,Point p){
		Integer value = (p.y - a.y)*(b.x - a.x)-(b.y-a.y)*(p.x-b.x);
		if(value>=0) {
			return value;
		}
		return value*-1;
	}
//	connaitre si le point est interne au triangle
	public Integer insideTriangle(Point a,Point b,Point c,Point p) {
		int a1 = this.searchSide(c, a, p)*this.searchSide(c, a, b);
		int a2 = this.searchSide(a, b, p)*this.searchSide(a, b, c);
		int a3 = this.searchSide(c, b, p)*this.searchSide(c, b, a);
		if(a1>0 && a2>0 && a3 >0) {
			return 1;
		}else {
			return -1;
		}
	}
//	effacer les point qui sont a l'interieur du triangle
	public void deletePointInside(ArrayList <Integer> indice,ArrayList <Point> point) {
		while(!indice.isEmpty()) {
			point.remove(indice.size()-1);
		}
		indice.clear();
	}
//	Algorithme du quickhull
	public void quickHull(Point A,Point B,int side){
		this.n = this.point.size();
//		 @find:int pour determiner si on a trouve un point
//		max_dist : recuperer le maximun de distance
	    Integer find = -1, max_dist =0;
	    for(int i=0;i< this.n; i++) {
	    	int temp= this.distance(A, B, this.point.get(i));
	    	int side_local = this.searchSide(A, B, this.point.get(i));
	    	if(side == side_local && temp>max_dist) {
	    		find = i;
	    		max_dist = temp;
	    	}
	    }
	    if(find==-1) {
	    	hull.add(B);
	    	hull.add(A);
	    	return;
	    }
	    else {
	    	hull.add(this.point.get(find));
	    	System.out.format(Locale.FRANCE, "Les points qui sont a linterrieur du triangle forme par {A:%s,\n B: %s,\n P :%s} sont :\n",A.toString(),B.toString(),this.point.get(find).toString());
	    	for(int i=0;i< this.n; i++) {
	    		if(this.insideTriangle(A, B, this.point.get(find), this.point.get(i))>0) {
	    			System.out.format(Locale.FRANCE, "{%s}\n",this.point.get(i).toString());
	    		}
	    	}
	    }
	    
//	    la recursivite 
	    
	    this.quickHull(this.point.get(find),A,-this.searchSide(this.point.get(find), A, B));
	    this.quickHull(this.point.get(find),B,-this.searchSide(this.point.get(find), B, A));
	}
	public QuickHull(ArrayList <Point> points){
		this.point = points;
		this.n = points.size();
	}
	}