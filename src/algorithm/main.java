package algorithm;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class main {
	public static int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	public static boolean isWindows() {
		return (System.getProperty("os.name")=="Windows")?true:false;
	}
	public static String pathFileCsv(String Filename) {
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
//		pour teste
		System.out.println("le dossier est:"+path);
		String os = System.getProperty("os.name");
		System.out.println("les systems est :"+ os);
//		code de fin test
		String separator=isWindows()?"\\":"/";
		return (path.toString()+separator+"data"+separator+Filename);
	}
	public static void generateCsv(String Filename, int col,int row) {
		String absoluPathFile = pathFileCsv(Filename);
		System.out.println("le chemin absolu  est :"+ absoluPathFile);
		File ouputFile = new File(absoluPathFile);
		try {
			PrintWriter writer = new PrintWriter(ouputFile);
			for(int i=0;i<row;i++) {
				for(int j=0;j<col;j++) {
					writer.printf("%d,%d",getRandomNumberUsingNextInt(col,2*row),getRandomNumberUsingNextInt(col,2*row));
				}
				writer.printf("\n");
			}
			writer.close();
		}catch(FileNotFoundException e1) {
			System.err.println(e1);
			
		}
	}
	public static ArrayList <Point> scannerCsv(String filename){
		ArrayList <Point> hull = new ArrayList <Point>(); 
		BufferedReader br = null;
		String absoluPathFile = pathFileCsv(filename);
		try {
			br = new BufferedReader(new FileReader(absoluPathFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
		String line;
		String splitBy= ",";
		try {
			while ((line = br.readLine()) != null){
				String[] coor = new String[2];
//				System.out.println("la line est :"+line);
				coor = line.split(splitBy);
//				System.out.println("le point est :"+coor.toString());
				hull.add(new Point(Integer.parseInt(coor[0]),Integer.parseInt(coor[1])));  //find and returns the next complete token from this scanner  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return hull;
		}  
	public static void affiche_resultat(ArrayList<Point> hull) {
		ArrayList<Point> newList = (ArrayList<Point>) hull.stream()
                .distinct()
                .collect(Collectors.toList());
		System.out.println("Les points de l'envoloppe convexe sont:");
		for(int i=0;i<newList.size();i++) {
			System.out.format(Locale.FRANCE,"{%d:%d}\n",newList.get(i).x,newList.get(i).y);
		}
	}
	
	public static void main(String[] args){
		ArrayList <Point> points = new ArrayList<Point>();
		String Filename = "output.csv";
		generateCsv(Filename,2,250);
		points = scannerCsv(Filename);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		QuickHull conver = new QuickHull(points);
		int n = points.size();
		System.out.format("le nombre des points dans l'espace sont %d\n",n);
		Date d1 = Calendar.getInstance().getTime();
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
		}
		Date d2 = Calendar.getInstance().getTime();
		long duration = d2.getTime() - d1.getTime();
		long duree = TimeUnit.MILLISECONDS.toMillis(duration);
		affiche_resultat(conver.hull);
		System.out.println("Temps d'excecution est de "+duree+" MilliSeconde");
		}
	}
