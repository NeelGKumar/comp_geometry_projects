
import java.util.*;
/**
 *
 * @author gkumar
 */
public class Face {

     HalfEdge l, m, n;
     ArrayList<Face> edgesOut = new ArrayList<Face>();


     Face (HalfEdge x, HalfEdge y,HalfEdge z){
          l = x;
          m = y;
          n = z;
          l.face = this;
          m.face = this;
          n.face = this;
          l.next = m;
          m.next = n;
          n.next= l;
     }

     public void AddEdge(Face f){
          edgesOut.add(f);
     }

     public int InTriange (Vertex p){

          int c1 = SameSide(l.v, m.v, p, n.v);
          int c2 = SameSide(m.v, n.v, p, l.v);
          int c3 = SameSide(n.v, l.v, p, m.v);
          if (c1 == 1 && c2 == 1 && c3 == 1){
               return 1;
          }

          if (c1 ==0){
               if (c2 > 0 && c3 > 0){
                    return 2;
               }
          }

          if (c2 ==0){
               if (c1 > 0 && c3 > 0){
                    return 3;
               }
          }

          if (c3 ==0){
               if (c1 > 0 && c2 > 0){
                    return 4;
               }
          }

          return -1;
     }

     public int SameSide (Vertex a, Vertex b, Vertex p, Vertex q){
          double abx = b.x - a.x;
          double aby = b.y - a.y;
          double apx = p.x - a.x;
          double apy = p.y - a.y;
          double aqx = q.x - a.x;
          double aqy = q.y - a.y;

          double Cross = abx*apy - aby*apx;
          double Cross_ref = abx*aqy - aby*aqx;

          if (Cross == 0){
               return 0;
          }

          if (Cross*Cross_ref < 0){
               return -1;
          }

          else {
               return 1;
          }

     }



     public double Angles (Vertex a, Vertex b, Vertex c){

          Vector v = new Vector (0.0, 0.0);
          double a1= v.Dot(a,b,c);
          double a2= v.Dot(b,c,a);
          double a3= v.Dot(c,a,b);

          if (a1 >= a2){
               if (a1 >= a3){
                    return a1;

               }
               else if (a1 < a3){
                    return a3;
               }
          }
          else {
               if (a2 >= a3){
                    return a2;
               }
               else {
                    return a3;
               }
          }
          return 0.0;

     }
}
