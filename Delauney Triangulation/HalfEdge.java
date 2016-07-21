
/**
 *
 * @author gkumar
 */
public class HalfEdge {

     Vertex v;
     HalfEdge pair = null;
     Face face;
     HalfEdge next;
     boolean visited = false;

     HalfEdge ( Vertex a, HalfEdge b, Face c){
          v = a;
          if (b != null){
               if (b.pair != null){
               pair = b.pair;
               }
          }
          face = c;
     }

     public void SetPair(HalfEdge h){
          pair = h;
          h.pair = this;
     }

     public void MarkVisited(){
          visited = true;
          if (pair != null){
               pair.visited = true;               
          }
     }

     public HalfEdge ChildPair(){
         if (pair == null){
             return null;
         }


         return null;

     }




}
