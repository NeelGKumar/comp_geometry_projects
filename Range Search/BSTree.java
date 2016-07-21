/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;

/**
 *given s list of sorted array it makes a BST
 * @author gkumar
 */
public class BSTree {
    BSTnode root;

    BSTree (Asteroid[] A, Asteroid[] B){
        if (A == null || B == null){
            System.out.println("NULL TREE");
            System.exit(1);
        }
        
       

        //System.out.println("BSTREE");
        root = Tree(A);
        //Tree Traversal
        //System.out.println("TREE TRAVERSAL");
        inorder(root);

    }

    public void inorder (BSTnode node){

          if ( node.left  != null){
                inorder(node.left);
          }
          if(node.left != null && node.right != null) {
               //System.out.println("\n"+ node.A.Name);
               for (int i = 0; i < node.Array.length; i ++){
                     //System.out.println( " " +node.Array[i].A.y +"[ " + node.Array[i].left + " , " + node.Array[i].right + " ]" );
               }
          }
          if (node.right !=null){
               inorder(node.right);
               
         }
          
    }

    public BSTnode Tree(Asteroid[] A){

        //form the associated array

        if (A.length == 1){
            //System.out.println(A[0].Name);
            FracCas[] FC = new FracCas[1];
            FC[0] = new FracCas(A[0],-1, -1);
            return new BSTnode(A[0], null, null, FC);
        }

        else {
            //System.out.println("mid" + A[A.length/2].Name);
            Asteroid[] left = new Asteroid[A.length/2];
	      System.arraycopy(A, 0, left, 0, left.length);

            Asteroid[] right = new Asteroid[A.length-left.length];
	      System.arraycopy(A, left.length, right, 0, right.length);

            
            BSTnode Aleft = Tree(left);
            BSTnode Aright = Tree(right);

            //merge
            FracCas[] M = Merge(Aleft.Array, Aright.Array);

            //System.out.println("mid" + A[A.length/2].Name);

            BSTnode n = new BSTnode(A[A.length/2], Aleft, Aright, M);
            for ( int i = 0; i < M.length; i++){
                if (M[i].left >=0 && M[i].right >= 0){
                   // System.out.println("    " + M[i].A.Name  +  "  " + M[i].A.y+ "  "+ Aleft.Array[ M[i].left].A.y +  "   " + Aright.Array[M[i].right].A.y);
                }
               // else {
                    //System.out.println("    " + M[i].A.Name  +  "  " + M[i].A.y+ "  "+ M[i].left +  "   " + M[i].right);
               // }

            }
            //System.out.println("MERGE  " + n.Array.length  );
            return n;

        }
        
    }


    private FracCas[] Merge(FracCas[] left, FracCas[] right) {
	int leftIndex = 0;
	int rightIndex = 0;
	int mergeIndex = 0;
      int gLeft = 0, gRight = 0;

        FracCas[] list = new FracCas[left.length + right.length];

        //System.out.println("t   MERGE  "   );
        double l = 0, r = 0;
	while (leftIndex < left.length && rightIndex < right.length) {

            l = left[leftIndex].A.y;
            r = right[rightIndex].A.y;
                      
	    if (l < r) {
		list[mergeIndex] =new FracCas( left[leftIndex]);
                list[mergeIndex].left = leftIndex;
                while (right[gRight].A.y < l && gRight < right.length -1){
                    gRight++;
                }
                if (right[gRight].A.y >= l){
                   list[mergeIndex].right = gRight;
                }
                else{
                    list[mergeIndex].right = -1;
                }
		leftIndex++;
	    }
	    else {
		list[mergeIndex] = new FracCas(right[rightIndex]);
                list[mergeIndex].right = rightIndex;
                while (left[gLeft].A.y < r && gLeft < left.length-1){
                    gLeft++;
                }
                if (left[gLeft].A.y >= r){
                   list[mergeIndex].left = gLeft;
                }
                else{
                    list[mergeIndex].left = -1;
                }
                
		rightIndex++;
	    }
	    mergeIndex++;
	}

	FracCas[] rest;
	int restIndex;
      int flag = 0;
	if (leftIndex >= left.length) {
          flag = 1;
	    rest = right;
	    restIndex = rightIndex;
        }
	else {
	    rest = left;
	    restIndex = leftIndex;
	}

	for (int i=restIndex; i<rest.length; i++) {
	    list[mergeIndex] = new FracCas(rest[i]);
            if ( flag == 1){
                list[mergeIndex].right = i;
                while (left[gLeft].A.y < r && gLeft < left.length-1){
                    gLeft++;
                }
                if (left[gLeft].A.y >= r){
                   list[mergeIndex].left = gLeft;
                }
                else{
                    list[mergeIndex].left = -1;
                }

            }

            else{
                list[mergeIndex].left = i;
                while (right[gRight].A.y < l && gRight < right.length-1){
                    gRight++;
                }
                if (right[gRight].A.y >= l){
                   list[mergeIndex].right = gRight;
                }
                else{
                    list[mergeIndex].right = -1;
                }

            }
	    mergeIndex++;

	}
      //System.out.println("t   MERGE  END  " + t  );
        return list;
    }


    public ArrayList<String> Query (double r1, double r2, double q1, double q2){

         ArrayList<String> P = new ArrayList<String>();
         BSTnode Rsplit = SplitNode(root, r1, r2);
         for ( int i = 0; i < Rsplit.Array.length; i++){

         }
        // System.out.println("Splitroot " + Rsplit.A.Name);
         boolean flag = true;
         int start = 0, end = Rsplit.Array.length-1;
         FracCas Present = null;
         while(flag){              
              int mid =start+ (end - start)/2;
              if (mid > end){
                   flag= false;
                   //System.out.println(" not in the range");
                   break;
              }
              //System.out.println("start  " + start+" mid " + mid+"  end  "+ end);
              if ( Rsplit.Array[mid].A.y >= q1 ){
                   if (mid != 0){
                        if (Rsplit.Array[mid-1].A.y < q1){
                             Present = Rsplit.Array[mid];
                             flag = false;
                        }
                        else{
                             end = mid-1;
                         }
                   }
                    else {
                        Present = Rsplit.Array[mid];
                        flag = false;
                    }
                }
               else{
                   start = mid+1;
               }
         }

         if (Present == null) {
              //System.out.println("No asteroid in range");
              return null;
              
         }
    //    System.out.println("Split   " + Rsplit.A.Name +" Present Y " +Present.A.y +" Len "+Rsplit.Array.length);
  //       System.out.println("start y" + Present.A.y);
//        System.out.println( "left y" + Rsplit.left.Array[Present.left].A.y + " index "  + Present.left);
      //  System.out.println("right y" + Rsplit.right.Array[Present.right].A.y + " index "  +Present.right);

         BSTnode vL = Rsplit, vR = Rsplit;
        FracCas vLY = Present, vRY = Present;
         
         if (Rsplit.left == null && Rsplit.right == null){
              if (Rsplit.A.x  >=  r1 && Rsplit.A.x <= r2 ){
                    if(Present.A.y >= q1 && Present.A.y <= q2 ){
                       //  System.out.println("----add Root " + vL.A.Name);
                          P.add(Rsplit.A.Name);
                    }
                    else {
                         // System.out.println("No asteroid in range");
                    }
              }
              else{
               //System.out.println("No asteroid in range");
              }
         }

         else{
             if (vLY.left >=0){
                  vLY = vL.Array[vLY.left];

              vL = vL.left;
             
              while (vL.left != null && vL.right != null  ){
                   if (vL.A.x  >= r1){
                        //report subtree
                       // System.out.println("rootL " + vL.A.x + "left index" + vLY.left);
                        ReportSubtree(vL.right, P, vLY.right, q2);
                        
                        vLY = vL.Array[vLY.left];
                        vL = vL.left;
                        //System.out.println("Left Index " + vLY.left + "size" + vL.Array.length);
                        
                   }
                   else {
                        vLY = vL.Array[vLY.left];
                        vL = vL.right;
                       // System.out.println("rootLLRLLLL " + vL.A.x);
                        
                   }
              }
              if (vL.A.x  >=  r1 && vL.A.x <= r2 ){
                   if(vL.A.y >=q1 && vL.A.y <= q2 ){
                        //System.out.println("add Root " + vL.A.Name);
                        P.add(vL.A.Name);
                   }
              }
              }

             if (vRY.right >= 0) {
                  vRY = vR.Array[vRY.right];

              vR = vR.right;
              // System.out.println("root 11" + vR.A.x);
              while (vR.left != null && vR.right != null){
                   //System.out.println("RRR  " + vR.A.x + " r2  " + r2);
                   if (vR.A.x <= r2 ){
                         
                        //report subtree
                       // System.out.println("rootR " + vR.A.x);
                        ReportSubtree(vR.left, P, vRY.left, q2);
                        vRY = vR.Array[vRY.right];
                        vR = vR.right;
                        
                   }
                   else {
                        vRY = vR.Array[vRY.right];
                        vR = vR.left;
                         //System.out.println("rootRLLLL 222 " + vR.A.x);
                       
                   }
              }
              if (vR.A.x  >=  r1 && vR.A.x <= r2 ){
                   if(vR.A.y >=q1 && vR.A.y <= q2 ){
                        //System.out.println("RRRRRRRRRadd Root " + vL.A.Name);
                        P.add(vR.A.Name);
                   }
              }
              }



         }       
         return P;
    }

    private BSTnode SplitNode ( BSTnode root, double r1, double r2 ){         
         while ( root.A.x  >= r2 || root.A.x <  r1){
              if (root.left == null && root.right == null ){
                   return root;
              }
              if ( root.A.x > r2){
                   root = root.left;
              }
              else root = root.right;
              //System.out.println("root-->" + root.A.x);
         }
         return root;
    }

    private void ReportSubtree (BSTnode node, ArrayList<String> Names, int i, double q2){
         FracCas[] N = node.Array;
         while (i > -1 && i < N.length){
             if ( N[i].A.y <= q2){
                 Names.add(N[i].A.Name);
                // System.out.println("subtreePresent  " + N[i].A.x);
             }
                i++;
          }        
    }


}
