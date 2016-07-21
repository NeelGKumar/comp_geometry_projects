/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medialaxis;
import java.io.*;
import java.util.*;

/**
 *
 * @author gkumar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

         Scanner sc = null;
        File inFile = new File(args[0]);
        sc = new Scanner(inFile);
        //sc = new Scanner ( System.in);
        int n = sc.nextInt();
        ArrayList<Vertex> P = new ArrayList<Vertex>();
        for ( int i = 0; i < n; i ++){
             P.add(new Vertex(i,sc.nextDouble(),sc.nextDouble(),null, null));
        }
        Polygon G = new Polygon(P);
//        Vertex v1 = new Vertex(0, 0.0 ,0.0,null, null);
//        Vertex v2 = new Vertex(0, 1.0 ,2.0,null, null);
//        Element a = new Element (v2, v1);
//        Vertex[] V = a.getEdge(v1,v2);
//        System.out.println("vertex " + V[0].x + " , " + V[0].y +" | "  + V[1].x + " , " + V[1].y );
        Chain[] t = new Chain[G.chainlist.size()];
        G.chainlist.toArray(t);
         System.out.println("\n merge divide");
        Chain[] Final = mergeDivide(t);
         System.out.println("\n Final");
         //print(Final);
         Element e = Final[Final.length-1].C.get(Final[Final.length-1].C.size()-1);
         System.out.println("e " + e.a.id + " - " + e.b.id);// + " , "+ e.prev.a.id + " - " + e.prev.b.id );
//         while(e != null){
//              if(e.isVertex){
//                   System.out.print(e.a.id + " | ");
//              }
//              else {
//                   System.out.print(e.a.id + " -  "+ e.b.id +" | ");
//              }
//              e = e.prev;
//         }
//         for (int i = 0; i < Final.length; i++){
//              te = Final[i];
//
//         }

    }
    static public Chain[] mergeDivide(Chain[] list){
         if (list.length == 1) {
            //System.out.println("t   MERGESORT  END CASE 1  " + t  );
	    return list;
	}
        Chain[] left = new Chain[list.length/2];
	    System.arraycopy(list, 0, left, 0, left.length);
	    Chain[] right = new Chain[list.length-left.length];
	    System.arraycopy(list, left.length, right, 0, right.length);
	    left = mergeDivide(left);
	    right = mergeDivide(right);
	    list = Merge(left, right, list);
            //System.out.println("t   MERGESORT  END" + t  );
	    return list;
    }


    static public Chain[] Merge(Chain[] c1, Chain[] c2, Chain[] list){
         print (c1);
         print(c2);
         Chain[] mC = new Chain[c1.length+c2.length];
         System.arraycopy(c1, 0, mC, 0, c1.length);
         System.arraycopy(c2, 0, mC, c1.length,c2.length);
         System.out.println("merge");
         c1[c1.length-1].C.get(c1[c1.length-1].size-1).next = c2[0].C.get(0);
         c2[0].C.get(0).prev = c1[c1.length-1].C.get(c1[c1.length-1].C.size()-1);
         Chain c= new Chain();
         c.mergeVoronoi(c1,c2);
         print(mC);
         return mC;
    }

    static public void print( Chain[] c){
          System.out.println("ONE");
         for (int i = 0; i < c.length; i++){
              c[i].printChain();
         }
          System.out.println("");

    }

}
