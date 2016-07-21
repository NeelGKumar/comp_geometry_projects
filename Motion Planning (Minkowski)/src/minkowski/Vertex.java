package minkowski;
/**
 *
 * @author gkumar
 */
public class Vertex {

     int n;
     Double x,y;
     Vertex next, prev;
     Double ang;

     Vertex (Double a, Double b, int m){
          x = a;
          y = b;
          n = m;
     }
     Vertex (Vertex v){
          n = v.n;
          x = v.x;
          y = v.y;
     }
     public boolean equals( Vertex p){

        if ( (Math.pow((p.x - x),2.0) + Math.pow(p.y - y, 2.0)) <= 10e-12){
            return true;
        }

        else {
            return false;
        }

    }

     public void print(){
         if(ang != null){
                System.out.println("Vertex : " + n + ":  " + x + " ,  " + y + " Ang  " + ang*180.0/Math.PI  );
            }
            else{
                System.out.println("Vertex : " + n + ":  " + x + " ,  " + y + " Ang  " + ang);
            }
         
     }

    public void angle(){
         double c = Cross();
         double d = Dot();
         ang = Math.acos(d);
         //this.print();
         //System.out.println(ang + "  c " + c + " d " + d);
         if (c<0 && d == 0){
              ang = ang +Math.PI;
         }
         if (c<0 && d >0){
              ang =(2*Math.PI-ang) +1.5*Math.PI;
         }
         if ( c<0 && d <0){
              ang = (Math.PI-ang)+Math.PI;
         }
         //System.out.println(ang + "  c " + c + " d " + d);
    }




   public boolean isReflex(){
          Double Ax = x - prev.x;
          Double Ay = y - prev.y;
          Double Bx = next.x - x;
          Double By = next.y - y;

          Double cross = Ax*By - Ay*Bx;
          
          if (cross < 0) {
                       return true;
          }
          else {
               return false;
          }

     }


   public int isLeft (Vertex b){
        double ax = next.x - x;
        double ay = next.y-y;
        double bx = b.x - x;
        double by = b.y-y;
        double cross = ax*by - ay*bx;
        
        if (cross < 0){
              //System.out.println("NotLeft cross " + cross);
             return -1 ;
        }
        else if (cross > 0){
             //System.out.println("isLeft cross " + cross);
             return 1;
        }
        else {
             //System.out.println("ZeroLeft cross " + cross);
             return 0;
        }
     }

     public double Cross (){
         double ax = next.x - x;
          double ay = next.y-y;
          double cross = ay/Math.sqrt(ax*ax+ay*ay);
          return cross;
     }

     public double Dot ( ){
          double ax = next.x-x;
          double ay = next.y-y;
          double dot = ax/Math.sqrt(ax*ax+ay*ay) ;
          return dot;
     }

     public double angleG(Vertex a, Vertex b){
         double c = CrossG(a, b);
         double d = DotG(a,b);
         ang = Math.acos(d);
         //System.out.println(ang + "  c " + c + " d " + d);
         if (c<0 && d == 0){
              ang = ang +Math.PI;
         }
         if (c<0 && d >0){
              ang = ang +1.5*Math.PI;
         }
         if ( c<0 && d <0){
              ang = ang+Math.PI/2;
         }
         return ang;
    }

     public double CrossG (Vertex a, Vertex b){
          double Ax = a.x - this.x;
          double Bx = b.x - this.x;
          double Ay = a.y - this.y;
          double By = b.y - this.y;
          double cross = (Ax*By - Ay*Bx)/(Math.sqrt(Ax*Ax+Ay*Ay)*Math.sqrt(Bx*Bx+By*By));
          return cross;
     }

     public double DotG ( Vertex a, Vertex b){
          double Ax = a.x - this.x;
          double Bx = b.x - this.x;
          double Ay = a.y - this.y;
          double By = b.y - this.y;
          double dot = (Ax*Bx + Ay*By)/(Math.sqrt(Ax*Ax+Ay*Ay)*Math.sqrt(Bx*Bx+By*By));
          return dot;
     }
     public Vertex plus(Vertex b){
     return new Vertex( this.x + b.x, this.y + b.y,0);
     }

     public void sub(Vertex b){
     x = x - b.x;
     y = y - b.y;
     }
}

