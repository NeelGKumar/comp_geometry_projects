/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author gkumar
 */
public class FracCas {
    Asteroid A;
    int left, right;

    FracCas ( Asteroid a, int l, int r){
        A = a;
        left = l;
        right = r;
    }
    FracCas(FracCas a){
         A = a.A;
         left = a.left;
         right = a.right;
    }

}
