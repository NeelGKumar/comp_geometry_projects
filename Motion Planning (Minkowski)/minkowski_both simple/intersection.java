/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minkowski;

/**
 *
 * @author goldy
 */
public class intersection {

    Double tmin = null, tmax = null;
    Vertex v1, v2;
    int left = -2, right = -2;

    public intersection(){

    }
    public void addT(double t, int l){
        if ( l== 0){
            if (left == -2){
                left = 0;
            }
            else {
                return;
            }
        }
        if (l ==1){
            if(left == -2){
                left = l;
            }
       else{
                System.out.println("return ");
          return;
        }
        }
        else if (l ==-1){
            if (right ==-2){
                right = 1;
            }
            else{
                System.out.println("return ");
                return;
            }
        }
        if (tmin == null && tmax ==null){
            tmin = t;
        }
       else if (tmax == null){
           if (t < tmin){
               tmax = tmin;
               tmin = t;
           }
            else {
               tmax = t;
            }
        }

       else if (t < tmin){
           tmin = t;
       }
       else if (t > tmax){
           tmax = t;
       }
    }
    public void pointInter(Vector V){
        //System.out.println(" t " + tmin + " " + tmax);
        if(tmin == null && tmax == null ){
            return;
        }
        if(tmax == null){
            if  ( left ==1){
                tmax = tmin;
                tmin = 0.0;
            }
            else{
                tmax = 1.0;
            }

        }
        double v1x = V.a.x + tmin*V.x*V.len;
        double v1y = V.a.y + tmin*V.y*V.len;
        double v2x = V.a.x + tmax*V.x*V.len;
        double v2y = V.a.y + tmax*V.y*V.len;
        v1 = new Vertex(v1x,v1y,1010);
        v2 = new Vertex(v2x,v2y,1010);

    }
    public void print(){
         if(tmin != null && tmax !=null){
            System.out.println("inter  "+v1.x + " " + v1.y + " " + v2.x + " " + v2.y );
            //myOut.println(t.v1.x + " " + t.v1.y + " " + t.v2.x + " " + t.v2.y );
          }
    }
    public double mergeInterval(intersection[] s, Vector v){

         double min = s[0].tmin, max = s[0].tmax, tot=0;
         if (s.length ==0){
              System.out.println(" no inter " );
              return tot;
         }

         if (s.length ==1){
               tot = s[0].tmax-s[0].tmin;
              System.out.println("t  tot " + tot*v.len);
              return tot;
         }
         double pmin, pmax;
         for (int i = 1; i < s.length; i++){
              pmin = s[i].tmin;
              pmax = s[i].tmax;
              if(pmin < max){
                   if(pmax > max){
                        max = pmax;
                   }
              }
              else{
                   tot = tot + max - min;
                   min = pmin;
                   max = pmax;
              }
         }
         tot = tot + max - min;
         System.out.println("t  tot " + tot*v.len);
         return tot;
    }

}
