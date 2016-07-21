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
public class Chain {
     ArrayList<Element> C;
     int size = 0;

     Chain(){
          C = new ArrayList<Element>();
          Iterator<Element> itr = C.iterator();
          if(itr.hasNext()){
               Element e = itr.next();
               Element present = e;
                   
               while(itr.hasNext()){
                    present  = itr.next();
                    present.prev = e;
                    e.next = present;
                    e = present;
               }
          }

     }

     public void appendChain(Element e){
          C.add(e);
          size = size+1;
         // System.out.println(" appending");
     }
     public void printChain(){
           Iterator<Element> itr1 = C.iterator();
               //System.out.print("\n Chain-- ");
               while (itr1.hasNext()){
                    Element e = itr1.next();
                    System.out.print(e.a.id);
                    if (!e.isVertex){
                          System.out.print(" - " + e.b.id);
                    }
                    System.out.println( " | ");
               }
            
     }
     public void mergeVoronoi(Chain[] c1, Chain[] c2){
          Element e1 = c1[c1.length-1].C.get(c1[c1.length-1].C.size()-1);
          System.out.print("EDGE " + e1.a.id + " -  " + e1.b.id+ " | " );
          Element e2 = c2[0].C.get(0);
          System.out.println(e2.a.id + " -  " + e2.b.id );
          System.out.println(e2.vedge.get(0).a.id + " -  " + e2.vedge.get(0).b.id );
          VoronoiEdge ve = lineB(e1.vedge.get(e1.size-1),e2.vedge.get(0),e1,e2);
          e1.vedge.set(e1.vedge.size()-1, ve);
          e2.vedge.set(0,ve);
          VoronoiEdge vl = null,vr=null;
          int nl = e1.size-1;
          int nr = 0;
          
          Vertex l = null,r = null,inter = null;
          int close = 0;
          boolean flag = true;
          while(e1.prev != null && e2.next != null){
               System.out.println("   IN WHILE " + nl + " , " + nr);
               ve.print();
               while (l == null && nl>-1){
                    nl = nl-1;
                    vl = e1.vedge.get(nl);
                    vl.print();
                    l = ve.lineInter(vl);                    
                    System.out.println("Check nl : " + nl);
               }
               while (r == null && nr < e2.size){
                    nr = nr+1;
                    vr = e2.vedge.get(nr);
                    vr.print();
                    r = ve.lineInter(vr);
                    System.out.println("Check nr : " + nr);
               }               
               close = ve.Closest(l, r);
               System.out.println("Check before  :" + nl + " + " + nr);
               if (close ==1){
                    inter = l;
                    vl.b = l;
                    ve.b =l;
                    e1 = e1.prev;
                    nl = e1.search(vl);
               }
               if (close ==2){
                    inter = r;
                    vr.b = r;
                    ve.b =r;
                    e2 = e2.next;
                    nr = e2.search(vr);
               }
               System.out.println("Check after  :" + nl + " + " + nr);
               ve = lineB(e1.vedge.get(nl),e2.vedge.get(nr),e1,e2);
               ve.a = inter;
               System.out.println("Check");
               ve.print();
               
               //flag = false;

          }
          


     }

     public VoronoiEdge lineB (VoronoiEdge v1, VoronoiEdge v2, Element e1, Element e2){
          v1.print();
          v2.print();
          Double x = (v1.vx + v2.vx);
          Double y = (v1.vy + v2.vy);
          VoronoiEdge ve = new VoronoiEdge(1,v1.a,new Vertex(-1,v1.a.x+x,v1.a.y+y,null,null),null,e1,e2);
          ve.print();
          return ve;

     }

    

   


}