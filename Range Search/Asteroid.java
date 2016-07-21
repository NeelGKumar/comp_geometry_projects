/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author gkumar
 */
public class Asteroid {
    String Name ;
    double x, y, Q, R;

    Asteroid (String name, double x1,double y1){
        Name = name;
        x = x1;
        y = y1;
        R = Math.sqrt(x*x + y*y);
        Q = 180/Math.PI*Math.acos(x/R);
        //System.out.println("   Q   " + Q);
        if ( y <0){
             if (x > 0){
                  Q=Q+270;
             }
             if (x<0){
                  Q=Q+90;
             }
        }
        x=R;
        y=Q;
        //System.out.println("R" + R + " Q" + Q);
        
    }

    Asteroid (Asteroid A){
         Name = A.Name;
         x = A.x;
         y = A.y;
         Q = A.Q;
         R = A.R;
    }

}


