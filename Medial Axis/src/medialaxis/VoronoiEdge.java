/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medialaxis;

/**
 *
 * @author gkumar
 */
public class VoronoiEdge {
    int id; //1-ray,2-segment,3-parabola
    Vertex a,b,c;
    Element e1,e2;
    Double vx = 0.0 , vy = 0.0, t = 0.0;
    double len = 0.;
    VoronoiEdge(int i,Vertex x, Vertex y, Vertex z, Element e1, Element e2){

         this.e1 = e1;
         this.e2 = e2;
         id = i;
         a = x;  b = y;  c = z;
        if (id != 3){
             vx = y.x-x.x;
             vy = y.y - x.y;
             t = Math.sqrt(vx*vx + vy*vy);
             vx = vx/t;
             vy = vy/t;


        }

     }

    public void print (){
         if(id ==1){
              System.out.println("RAY  " + a.id + " vec- " + vx +" ," +  vy+  " - ( " + b.x + " , " + b.y + " ) " );
         }
         else if (id ==2){
              System.out.println("Segment  " + a.id+ " vec- " + vx +" ," +  vy + " - ( " + b.x + " , " + b.y + " ) " );
         }
         else {
              System.out.println("Parabola  " + a.id + " ---  " + b.id + " - " + c.id  );
         }
    }

      public int Closest( Vertex a, Vertex b){
           if ( a == null && b == null){
                System.out.println("closest problem");
           }
           else if(a == null){
                 System.out.println("closest right: " + b.x + " , " + b.y);
                return 2;
           }
           else if (b == null){
                System.out.println("closest left: " + a.x + " , " + a.y);
                return 1;
           }
          Vertex t = this.a;
          double d1 = (a.x-t.x)*(a.x-t.x) + (a.y - t.y)*(a.y - t.y);
          double d2 = (b.x-t.x)*(b.x-t.x) + (b.y - t.y)*(b.y - t.y);
          if (d1<d2) {
               System.out.println("closest : left " + a.x + " , " + a.y);
               return 1;
          }
          else{
               System.out.println("closest : right " + b.x + " , " + b.y);
               return 2;
          }

     }

       public Vertex lineInter(VoronoiEdge v1){
          double ax = a.x;   double ay = a.y;
          double bx = v1.a.x;   double by = v1.a.y;
          double ux = vx;   double uy = vy;
          double vx = v1.vx;   double vy = v1.vy;
          double s = ((ax*vy-ay*ux)-(bx*vy-by*ux))/(vx*uy - ux*vy);
          double x = bx+vx*s;
          double y = by+vy*s;
          System.out.println("intersection" + x +" , " + y );
          Vertex V = new Vertex(-2,x,y,null,null);
          if (v1.id ==2 && t < s){
              return null;
           }          
          return V;
     }

//       public VoronoiEdge elementB( Element el, Element er){
//
//       }

}
