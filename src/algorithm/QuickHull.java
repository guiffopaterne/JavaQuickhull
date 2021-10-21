package algorithm;
import java.util.ArrayList;
import java.util.Locale;
// importer la librairie qui gere les listes
// la librarie pour gere les points dans un repere (x,y)
import java.awt.Point;


// creation de la classe responsable de l'agorithme du quickhull
public class QuickHull{
	 public int orientation(Point p, Point q, Point r)
	    {
	        int val = (q.y - p.y) * (r.x - q.x) -
	                  (q.x - p.x) * (r.y - q.y);
	        if (val == 0) return 0;
	        return (val > 0)? 1: 2; 
	    }

//	les donnes d'entree est une listes de pounts
	private ArrayList<Point> point = new ArrayList<Point>();
//	donnees de sortie de l'algorithme les points de l'enveloppe convexe
	public ArrayList<Point> hull = new ArrayList<Point>();
	public ArrayList<Point> hullJavis = new ArrayList<Point>();
	// la taille de la liste
	private int n = 0;
	private int min;
	
// SearchSide la fonction qui determine de quel cote de 
//	la droite se trouve le point
//	nous permet de decide si ce point est du bon cote ou si doit encore prs en consideration
	
	public Integer searchSide(Point a,Point b,Point p){
		Integer value = (p.y - a.y)*(b.x - a.x)-(b.y-a.y)*(p.x-a.x);
		
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
	private Double distanceToSegment(Point ps, Point pe, Point p) {
		if (ps.x==pe.x && ps.y==pe.y) return this.distance(ps,p);
		long sx=pe.x-ps.x,sy=pe.y-ps.y,ux=p.x-ps.x,uy=p.y-ps.y;
		long dp=sx*ux+sy*uy;
		if (dp<0) return this.distance(ps,p);
		long sn2 = sx*sx+sy*sy;
		if (dp>sn2) return this.distance(pe,p);
	 
		double ah2 = dp*dp / sn2;
		long un2=ux*ux+uy*uy;
		return Math.sqrt(un2-ah2);
	}
	 
	/**
	 * return the distance between two points
	 *
	 * @param p1,p2 the two points
	 * @return dist the distance
	 **/
	private Double distance(Point p1, Point p2) {
		long d2 = (p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y);
		return Math.sqrt(d2);
	}
	public int distance(Point a,Point b,Point p){
		return Math.abs(p.y - a.y)*(b.x - a.x)-(b.y-a.y)*(p.x-b.x);
	}
//	connaitre si le point est interne au triangle
	public int insideTriangle(Point a,Point b,Point c,Point p) {
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
	    int find = -1;
	    		double max_dist =-1;
	    for(int i=0;i< this.n; i++) {
	    	Double temp= this.distanceToSegment(A, B, this.point.get(i));
	    	int side_local = this.searchSide(A, B, this.point.get(i));
	    	if((side == side_local)&& temp>max_dist) {
	    		find = i;
	    		max_dist = temp;
	    	}
	    }
	    if(find==-1) {
	    	hull.add(B);
	    	hull.add(A);
	    	return;
	    }
//	    la recursivite 
	    
	    this.quickHull(this.point.get(find),A,-this.searchSide(this.point.get(find), A, B));
	    this.quickHull(this.point.get(find),B,-this.searchSide(this.point.get(find), B, A));
	}
	public void Jarvis() {
		int p = this.min, q, ii = 0;
		do
		{
			this.hullJavis.add(this.point.get(p));
			q = (p + 1) % n;
			for (int i=0; i<this.n; i++) {
				int dist = this.orientation(this.point.get(p), this.point.get(i), this.point.get(q));
				if(dist==2) q=i;
			}
			p=q;
		}while(p != this.min);
		
	}
	public QuickHull(ArrayList <Point> points, int min){
		this.point = points;
		this.n = points.size();
		this.min = min;
	}
	public QuickHull(ArrayList <Point> points){
		this.point = points;
		this.n = points.size();
	}
	}