import java.util.ArrayList;
//import java.util.Locale;
//import java.util.Scanner;

import java.awt.Point;



public class QuickHull{
	private ArrayList<Point> point = new ArrayList<Point>();
//			{0, 3},{1, 1},{2, 2}, {4, 4},{0, 0}, {1, 2}, {3, 1}, {3, 3}};
//	private Integer side =1;
	public ArrayList<Point> hull = new ArrayList<Point>();
	private int n = 0;
	
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
	
	public Integer distance(Point a,Point b,Point p){
		Integer value = (p.y - a.y)*(b.x - a.x)-(b.y-a.y)*(p.x-b.x);
		if(value>=0) {
			return value;
		}
		return value*-1;
	}
	
	public void quickHull(Point A,Point B,int side){
		
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
//	    else {
//	    	hull.add(this.point.get(find));
//	    }
	    this.quickHull(this.point.get(find),A,-this.searchSide(this.point.get(find), A, B));
	    this.quickHull(this.point.get(find),B,-this.searchSide(this.point.get(find), B, A));
	}
	public QuickHull(ArrayList <Point> points){
		this.point = points;
		this.n = points.size();
	}
	}