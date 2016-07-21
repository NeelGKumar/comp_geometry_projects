

import java.util.*;
import java.io.*;


/**
 *
 * @author gkumar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner ( System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Point> polygon = new ArrayList<Point>();
        ArrayList<HalfPlanes> halfplanes = new ArrayList<HalfPlanes>();
        ArrayList<LineSeg> lineS = new ArrayList<LineSeg>();

        double x,y;
        for ( int i = 0; i < n; i++){
            x = sc.nextDouble();
            y = sc.nextDouble();
            polygon.add(new Point(x, y));
        }


        for ( int i = 0; i < n-1; i++){
            lineS.add( new LineSeg(polygon.get(i), polygon.get(i+1)));
        }
        lineS.add( new LineSeg(polygon.get(n-1), polygon.get(0)));

        double a, b, c;
        for ( int i = 0; i < m; i ++){

            a = sc.nextDouble();
            b = sc.nextDouble();
            c = sc.nextDouble();
            halfplanes.add(new HalfPlanes(a, b, c));
        }
       
        /*
         *clipped polygon points
        */

        // temp variable        
        HalfPlanes hp = new HalfPlanes();
        
        int i = 0;
        while ( i < m){
            hp = halfplanes.get(i);
            lineS = hp.clipPolygon(lineS);
            i++;
        }
        
        double area = 0.0;
        for ( int j = 0; j < lineS.size(); j++){

            area = area + 0.5 * (lineS.get(j).b.x*lineS.get(j).a.y - lineS.get(j).a.x * lineS.get(j).b.y);
            
        }
        System.out.println(area);
    }
}
