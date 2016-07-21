
import java.util.ArrayList;

/**
 *
 * @author gkumar
 */
public class HalfPlanes {

    double a, b, c;
    LineSeg lineSeg;

    public HalfPlanes (double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;

        double xl1 = -1.0, xl2 = 1.0;
        double yl1 = -1.0, yl2 = 1.0;
        boolean at=false, bt=false, ct=false;

        if (a < -1E-6 || a> 1E-6) at = true;
        if (b < -1E-6 || b> 1E-6) bt = true;
        if (c < -1E-6 || c> 1E-6) ct = true;

        //if both a and b are very small
        if ((!at && !bt) || ( at && bt)) {
            yl1 = (c-a*xl1)/b;
            yl2 = (c-a*xl2)/b;
        }

        //else if b is small
        else if (!bt) {
            xl1 = c/a;
            xl2 = xl1;

        }

        //else if a is small
        else if (!at)
        {
            yl1 = c/b;
            yl2 = yl1;

        }
        Point p = new Point(xl1, yl1);
        Point q = new Point(xl2, yl2);
        lineSeg = new LineSeg(p, q);

    }

    public HalfPlanes (){

    }

    public int PMI (Point p){
        double ls = a * p.x + b * p.y - c;
        if (ls < 10e-6 && ls > -10e-6){
            return 0;
        }

        else if ( ls < 0){
            return 1;
        }

        else return -1;
    }

    public ArrayList<LineSeg> clipPolygon (ArrayList<LineSeg> edges ){
    
        if(edges == null || edges.isEmpty()){
            return edges;
        }

        
        int n = edges.size();
        
        Point p = new Point();
       
        LineSeg ls = new LineSeg();
        ArrayList<Point> clipped = new ArrayList<Point>();

        ArrayList<LineSeg> clippedLineS = new ArrayList<LineSeg>();

        for ( int i = 0; i < n; i++){
           
            ls = edges.get(i);
            p = ls.lineSegHalfPlaneIntersection(this);
           
            if(!clipped.isEmpty()){
                if(!clipped.get(clipped.size()-1).equals(ls.a)){
                    if (this.PMI(ls.a) == 1){
                        clipped.add(ls.a);
                     }
                }
            }

            else  {
                if (this.PMI(ls.a) == 1){
                    clipped.add(ls.a);
                }
            }
           
            
            
            if (p != null){
                
                if( !p.equals(ls.a)){
                clipped.add(p);
                }
                                                         
            }
         
         }

        int m = clipped.size();
        for ( int i = 0; i < m-1; i++){
            clippedLineS.add( new LineSeg(clipped.get(i), clipped.get(i+1)));
        }
        clippedLineS.add( new LineSeg(clipped.get(m-1), clipped.get(0)));
        
        
        return clippedLineS;
        
}
}
