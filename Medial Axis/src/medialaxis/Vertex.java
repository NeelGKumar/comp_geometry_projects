/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medialaxis;

/**
 *
 * @author gkumar
 */
public class Vertex {
     int id;
     Double x,y;
     boolean isReflex;
     Vertex next, prev;

     Vertex (int i,Double a, Double b, Vertex nxt, Vertex prv){
          id = i;
          x = a;
          y = b;
          next = nxt;
          prev = prv;
     }

}