
import java.lang.Math;
/**
 *
 * @author gkumar
 */
public class Vector {
     double x;
     double y;

     public Vector (double a, double b){
          x = a;
          y = b;
     }

     public Vector (Vertex a, Vertex b){
          x = b.x - a.x;
          y = b.y - a.y;
          double l = Math.sqrt(x*x+y*y);
          x = x/l;
          y = y/l;
     }

     public double Cross (Vector a, Vector b){
          double cross = a.x*b.y - a.y*b.x;
          return cross;
     }

     public double Dot ( Vertex a, Vertex b, Vertex c){
          if (b.n == 0){
              return 2.0;
          }
          Vector BA = new Vector(b,a);
          Vector BC = new Vector (b, c);
          double dot = BA.x*BC.x + BA.y*BC.y;
          return dot;
     }

}
