package minkowski;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gkumar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        //input-output
        Scanner sc = null;
        File inFile = new File(args[0]);
        sc = new Scanner(inFile);
        //sc = new Scanner ( System.in);
        String outFileName = "out.txt";
        PrintStream myOut = null;
        File outFile = new File(outFileName);
	  myOut = new PrintStream(outFile);
        Vector V = new Vector( new Vertex(0.0,0.0, 100), new Vertex(sc.nextDouble(), sc.nextDouble(),101));
        myOut.println(V.a.x + " " + V.a.y+ " " + V.b.x + " " + V.b.y);
        int n = sc.nextInt(), m = sc.nextInt();
        Polygon pA = new Polygon(new Vertex(sc.nextDouble(),sc.nextDouble(), -1), myOut);
        
        //reading polygon
        for (int i = 0; i < n-1; i ++){
             pA.add(new Vertex(sc.nextDouble(),sc.nextDouble(), 0-(2+i)));
        }
        pA.getConvex();
        Iterator<Polygon> itrA = pA.pList.iterator();
        ArrayList<Polygon> Alist = new ArrayList<Polygon>();
        Polygon temp;
        while(itrA.hasNext()){
             temp = itrA.next();
              //System.out.println("Polygon A" );
             //temp.print();
             Alist.add(temp.flipPolygon());
        }
        Polygon pB = new Polygon(new Vertex(sc.nextDouble(),sc.nextDouble(), 1), myOut);

        for (int i = 0; i < m-1; i ++){
             pB.add(new Vertex(sc.nextDouble(),sc.nextDouble(), i+2));
        }

         //subdivide B into convex pieces
         pB.getConvex();
         //Print Polygon A
        Vertex index = pA.head;
        pA.angle(false);
        index = pB.head;;
        ArrayList<intersection> inter = new ArrayList<intersection>();
        Iterator<Polygon> itrb;
        itrA = Alist.iterator();
        Polygon pb, pM, pa;
        int i = 0, j=0;
        while(itrA.hasNext()){
             j = j+1;
             pa = itrA.next();
             //System.out.println("Polygon A " + j);
             //pa.print();
             itrb = pB.pList.iterator();
                while(itrb.hasNext()){
                    i = i+1;
                    pb = itrb.next();
                    //System.out.println("Polygon " + i);
                    //pb.print();
                    //System.out.println("Minkowski " + i);
                    pM = pa.Minkoswski(pb);
                    //pM.print();
                    pM.intersection(V);
                    //pM.printInter();
                    if (pM.t.tmin!=null && pM.t.tmax!=null){
                         inter.add(pM.t);
                   }
                }
        }
        if (!inter.isEmpty()){
        intersection[] in = new intersection[inter.size()];
        inter.toArray(in);
        SortList S = new SortList(in);
       // System.out.println("Final");
//        for (int k = 0; k < S.sList.length; k++){
//            S.sList[k].print();
//        }
       double ans =  in[0].mergeInterval(S.sList, V);
       System.out.print(ans);
         }
        else{
             System.out.print(0.0);
        }
    }

}
