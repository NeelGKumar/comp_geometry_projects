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
public class Polygon {
     ArrayList<Vertex> Points;
     Vertex root;
     ArrayList<Chain> chainlist = new ArrayList<Chain>();

     Polygon(ArrayList<Vertex> polygon){
          Points = polygon;
          findReflex();
          Iterator<Vertex> itr = polygon.iterator();
          root = itr.next();
          root.prev = polygon.get(polygon.size()-1);
          Vertex present = root;
          while(itr.hasNext()){
               present  = itr.next();
               present.prev = root;
               root.next = present;
               root = present;
          }
          root.next = polygon.get(0);
//          for (int i = 0; i < 10; i++){
//               System.out.println(root.id);
//               root = root.next;
//          }
          formChainList();

     }

     public void addVertex(Vertex v){

     }

     public void formChainList(){

          while (root.isReflex){
               root = root.next;
          }
          int id = root.id;
         // System.out.println("root " + root.id + " Startid " + id);
          root = formChain(root);
         // System.out.println("root after " + root.id);
          while (root.id != id){
                root = formChain(root);
                //System.out.println("root " + root.id + " Start id  " + id);
          }
          Iterator<Chain> itr = chainlist.iterator();
          //chainlist.get(0).C.get(0).prev = chainlist.get(chainlist.size()-1).C.get(chainlist.get(chainlist.size()-1).C.size()-1);
          while(itr.hasNext()){
              Chain pC = itr.next();
               Iterator<Element> itr1 = pC.C.iterator();
               System.out.print("\n Chain-- ");
               while (itr1.hasNext()){
                    Element e = itr1.next();
                    System.out.print(e.a.id);
                    if (!e.isVertex){
                          System.out.print(" - " + e.b.id);
                    }
                    System.out.print( " | ");
               }
          }
     }

     public Vertex formChain(Vertex v){
          Chain C = new Chain();
          Element e = new Element(v,v.next, null,null);
          C.appendChain(e);
          v = v.next;
          while(v.isReflex){
               Element e1 = new Element(v,null, e,null);
               e.next = e1;
               Element e2 = new Element(v,v.next,e1, null);
               e1.next = e2;
               e1.Voronoi();
               C.appendChain(e1);
               C.appendChain(e2);
               v = v.next;
               e = e2;
          }
          chainlist.add(C);
          return v;
     }

     private void findReflex(){
          int num = Points.size();
          Vertex a = Points.get(num-1);
          Vertex b = Points.get(0);
          Vertex c = Points.get(1);
          b.isReflex = checkReflex(a,b,c);
          System.out.println(b.id + " - " + b.isReflex);

          for ( int i = 2; i < num ; i ++){
               a = b;
               b = c;
               c = Points.get(i);
               b.isReflex = checkReflex(a,b,c);
               System.out.println(b.id + " - " + b.isReflex);
          }
          a = b;
          b = c;
          c = Points.get(0);
          b.isReflex = checkReflex(a,b,c);
          System.out.println(b.id + " - " + b.isReflex);
     }

     private boolean checkReflex(Vertex a, Vertex b, Vertex c){
          Double Ax = b.x - a.x;
          Double Ay = b.y - a.y;
          Double Bx = c.x - b.x;
          Double By = c.y - b.y;

          Double cross = Ax*By - Ay*Bx;
          if (cross < 0) {
               return true;
          }
          else {
               return false;
          }

     }

}