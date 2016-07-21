/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author gkumar
 */
public class QueryRange {

    double q1, q2;
    double r1, r2;

    QueryRange(double a, double b, double c, double d, BSTree tree){
        double qS= a;
        double qE= b;
        r1 = c;
        r2 = d;
        double r = 0;
        r = qE- qS;
        q1 = qS - Math.floor(qS/360)*360;
        q2 = q1 + r;
        ArrayList<String> range = null;
       // System.out.println("AAAAAAAAAAA " + q1 + " end   " + q2);
        if (r<=0){
             q1 = 0; q2 = 0;r1 = 0; r2= 0;

        }
        else {
               if (r >= 360){
                    q1 = 0; q2 = 360;
                     range = tree.Query(r1, r2, q1, q2);
                     //System.out.println("Start" + q1 + " end   " + q2);
               }
               else {
                    if ( q2 < 360){
                        range = tree.Query(r1, r2, q1, q2);
                          //System.out.println("Start" + q1 + " end   " + q2);
                    }

                    else {
                         range = tree.Query(r1, r2, q1, 360);
                         range.addAll(tree.Query(r1, r2, 0, q2- Math.floor(q2/360)*360));
                         double t = q2- Math.floor(q2/360)*360;
                          //System.out.println("SPLITTTTTT " + q1 + " end   " + 360);
                         //System.out.println("SPLITTTTTT " + 0 + " end   " + t);
                    }
               }
        }


         if ( range != null && !range.isEmpty()){
              Collections.sort(range);
              Iterator<String> itr = range.iterator();
              while(itr.hasNext()){
                    System.out.print(itr.next() + " ");
               }
       }

       else {
                    System.out.print("No asteroids in range");
               }
     }
}





