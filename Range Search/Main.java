/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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

        Scanner sc = null;
        //File inFile = new File(args[0]);
        //sc = new Scanner(inFile);
        sc = new Scanner ( System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println(n + ","+ m);

        //ArrayList<Asteroid> U = new ArrayList<Asteroid>();
        Asteroid[] U = new Asteroid[n];
        for ( int i = 0; i < n; i ++){
           U[i] = new Asteroid(sc.next(),sc.nextDouble(), sc.nextDouble() );
        }


      


        SortList lx = new SortList(U, 'x');
        SortList ly = new SortList(U, 'y');

         for ( int i = 0; i < n; i ++){
           //System.out.println("U "  + U[i].Name);
        }


         for ( int i = 0; i < n; i ++){
           System.out.println(lx.sList[i].Name + " R " + lx.sList[i].x + " Q " + lx.sList[i].y);
        }



         for ( int i = 0; i < n; i ++){
           //System.out.println(ly.sList[i].Name);
        }

//        System.out.println(U.length + ","+ range.length);

        BSTree Tree = new BSTree (lx.sList, ly.sList);
        QueryRange[] range = new QueryRange[m];
        for (int i = 0; i < m ; i ++){
            System.out.print("Case " +( i+1) + " : ");
            range[i] = new QueryRange(sc.nextDouble(),  sc.nextDouble(),sc.nextDouble(), sc.nextDouble(), Tree);
            System.out.print("\n");
        }

        ArrayList<String> Search = Tree.Query(2, 1,0, 100);
        Iterator<String> itr = Search.iterator();
        while(itr.hasNext()){
             System.out.println(itr.next());
        }


    }

}
