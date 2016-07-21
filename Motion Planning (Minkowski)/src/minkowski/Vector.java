/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minkowski;
import java.lang.Math;
/**
 *
 * @author gkumar
 */
public class Vector {
    Vertex a,b;
     double x;
     double y;
     double len;

     public Vector (double ax, double bx){
          x = ax;
          y = bx;
     }

     public void print(){
         System.out.println(" vec " + x*len + " , " + y*len + " , " + len);
     }

     public Vector (Vertex al, Vertex bl){
         a = al;
         b = bl;
         x = bl.x - al.x;
         y = bl.y - al.y;
         len = Math.sqrt(x*x+y*y);
         if(len < 1e-6){
             return;
         }
         x = x/len;
         y = y/len;
     }

     public double Cross (Vector a, Vector b){
         //System.out.println("cross");
         //a.print();
         //b.print();
         double cross = a.x*b.y - a.y*b.x;
         return cross*a.len*b.len;
     }


     public void lineIntersection ( Vertex m, intersection t){

        Vertex n = m.next;
        Vector mn = new Vector(m,n);
        Vector ab = this;
        
        Vector am = new Vector ( a,m);
        Vector an = new Vector (a,n);
        
        Vector ma = new Vector (m, a);
        Vector mb = new Vector (m, b);

        double area1 = Cross(ab,am);
        double area2 = Cross(ab,an);
        double area1m = Cross(mn,ma);
        double area2m = Cross(mn,mb);
        double area  = Cross(ab,mn);

        if ( (area1>0 && area2 > 0) || (area1 < 0 && area2 < 0)) {
            return;
        }
        if ( (area1m>0 && area2m > 0) || (area1m < 0 && area2m < 0)) {
            return;
        }

        if (area < 10E-6 && area > -10E-6){
            double t1 = (m.x-a.x)/(ab.x*len);
            t.addT(t1,0);
            double t2 = (n.x-a.x)/(ab.x*len);
            t.addT(t2,0);
            //System.out.println("coincident " + t1 + t2);
            return;
         }

        double s = area1m/area;   // -ve since ax = a-x
       // mn.print();am.print();ab.print();

        int l = m.isLeft(this.a);
        //System.out.println("S " + s + " is left " + l);
        t.addT(s,l);
      

}
}
