
import java.io.*;
import java.util.*;


/**
 *
 * @author gkumar
 */
public class Main {

   public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = null;
        //File inFile = new File(args[0]);
        //sc = new Scanner(inFile);
        sc = new Scanner ( System.in);
        int n = sc.nextInt();
        ArrayList<Vertex> P = new ArrayList<Vertex>();
        for ( int i = 0; i < n; i ++){
             P.add(new Vertex(sc.nextDouble(),sc.nextDouble(), i+1));
        }
        Vertex Maxy=null, Miny = null, Maxx = null, Minx = null;
        Double My = -10000000.0, my = 10000000.0;
        Double Mx = -10000000.0, mx = 10000000.0;
        Iterator<Vertex> itr = P.iterator();
        while(itr.hasNext()){
             Vertex v = itr.next();
             if (v.x >= Mx)  {
                  Mx = v.x;
                  Maxx = v;
             }
             if (v.x <= mx) {
                  mx = v.x;
                  Minx = v;
             }

             if (v.y >= My)  {
                  My = v.y;
                  Maxy = v;
             }
             if (v.y <= my) {
                  my = v.y;
                  Miny = v;
             }
         }

        Double midx = 0.5*(Mx+mx), midy = 0.5*(My+my);
        Vertex b = new Vertex(mx, my,0), c = new Vertex (Mx, my,0);
        double Bx = midx + 100000.0*(mx - midx);
        double By = midy + 100000.0*(my - midy);
        double Cx = -Bx;
        double Cy = By;
        Vertex A = new Vertex(midx, 1000.0*My,0), B = new Vertex(Bx, By,0), C = new Vertex(Cx, Cy,0);
        HalfEdge H1 =  new HalfEdge (A, null, null), H2 = new HalfEdge (B, null, null), H3 = new HalfEdge (C, null, null);
        Face f = new Face (H1, H2, H3);
        Triangulation D = new Triangulation(f);
        Iterator<Vertex> itr1 = P.iterator();
        while(itr1.hasNext()){
             Vertex v = itr1.next();
             D.AddPoint(v);
        }
        ArrayList<Edges> edges = D.DelauneyFace();
        Edges[] a = new Edges[edges.size()];
        edges.toArray(a);
        SortList list = new SortList(a,'n');
        Iterator<Edges> itr2 = edges.iterator();
       
         for (int i =0; i < list.sList.length; i++){
               if (list.sList[i].n !=0){
               System.out.println(list.sList[i].n + " " +list.sList[i].m );
              }
         }

    }
}

