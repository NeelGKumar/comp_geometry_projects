/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medialaxis;

import java.util.*;

/**
 *
 * @author gkumar
 */
public class Element {
     Vertex a,b;
     boolean isVertex = false;
     Element next, prev;
     ArrayList<VoronoiEdge> vedge = new ArrayList<VoronoiEdge>();
     int size = 0;

     Element(Vertex a1, Vertex a2, Element prv, Element nxt){
          a = a1;
          if (a2 == null){
               System.out.println("element" + a1.id);
               isVertex = true;
          }
          else{
               b = a2;
               System.out.println("element" + a1.id + " - " + a2.id);
               Voronoi();
          }
          next = nxt;
          prev = prv;
          
     }
     public int search( VoronoiEdge ve){
          for (int i = 0; i < size; i++){
               if (vedge.get(i) == ve){
                    return i;
               }
          }
          return -1;
     }

     public void Voronoi(){
         if(isVertex){
             Vertex A,B,C;
             B = a; C = a.next; A = a.prev;
              System.out.println("voronoi -reflex " + prev.size);
             addVedge(prev.vedge.get(prev.size-1));
             // Vertex[] v = getEdge(B,C);
             //vedge.add(new VoronoiEdge(1,B, v[0],null));
             addVedge(next.vedge.get(0));
             
         }
         else {
              Vertex[] v = getEdge(a,b);
              addVedge(new VoronoiEdge(1,a, v[0],null,this,null));
              addVedge(new VoronoiEdge(1,b, v[1],null,this,null));
              
         }
         System.out.println("vedge 1 " + vedge.get(0).a.x + " ,  " +vedge.get(0).a.y + " | "+ vedge.get(0).b.x + " ,  " +vedge.get(0).b.y);
         System.out.println("vedge 2 " + vedge.get(1).a.x + " ,  " +vedge.get(1).a.y + " | "+ vedge.get(1).b.x + " ,  " +vedge.get(1).b.y);
     }

     public void addVedge(VoronoiEdge ve){
          vedge.add(ve);
          size = size+1;
     }
     public Vertex[] getEdge(Vertex a, Vertex b){
          Vertex[] V = new Vertex[2];
          double vx = b.x - a.x;
          double vy = b.y - a.y;
          Vertex v1 = new Vertex(-1,a.x + vy, a.y -vx,null,null);
          Vertex v2 = new Vertex(-1,b.x + vy, b.y -vx,null,null);
          
          double cross = -vx*vx - vy*vy;
          if (cross  < 0){
               v1.x = a.x - vy; 
               v1.y = a.y +vx;
               v2.x = b.x - vy;
               v2.y = b.y +vx;
          }
          V[0] = v1; V[1] = v2;
          return V;
     }


}
