/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minkowski;



/**
 *
 * @author gkumar
 */
public class SortList {
    intersection[] sList;

    SortList(intersection[] list){

        intersection[] temp = new intersection[list.length];
        System.arraycopy(list, 0, temp, 0, list.length);
        
            sList = mergeSort(temp);
     }



    public intersection[] mergeSort (intersection[] list){

        //System.out.println("t   MERGESORT  " + t  );
        
      if (list.length == 0){
           return null;
      }

	if (list.length == 1) {
            //System.out.println("t   MERGESORT  END CASE 1  " + t  );
	    return list;
	}
	else {
	    intersection[] left = new intersection[list.length/2];
	    System.arraycopy(list, 0, left, 0, left.length);
	    intersection[] right = new intersection[list.length-left.length];
	    System.arraycopy(list, left.length, right, 0, right.length);
	    left = mergeSort(left);
	    right = mergeSort(right);
	    merge(left, right, list);
            //System.out.println("t   MERGESORT  END" + t  );
	    return list;
	}
    }
    private void merge(intersection[] left, intersection[] right, intersection[] list) {
	int leftIndex = 0;
	int rightIndex = 0;
	int mergeIndex = 0;
        //System.out.println("t   MERGE  " + t  );
        double l = 0, r = 0;
	while (leftIndex < left.length && rightIndex < right.length) {

         //System.out.println("Y SORT");
         l = left[leftIndex].tmin;
         r = right[rightIndex].tmin;
         if ( l == r){
         l = left[leftIndex].tmax;
         r = right[rightIndex].tmax;
        }        
	 if (l < r) {
	   list[mergeIndex] = left[leftIndex];
	   leftIndex++;
	    }
	 else {
	   list[mergeIndex] = right[rightIndex];
	   rightIndex++;
	 }
	   mergeIndex++;
	}
	intersection[] rest;
	int restIndex;
	if (leftIndex >= left.length) {
	    rest = right;
	    restIndex = rightIndex;
	}
	else {
	    rest = left;
	    restIndex = leftIndex;
	}

	for (int i=restIndex; i<rest.length; i++) {
	    list[mergeIndex] = rest[i];
	    mergeIndex++;
	}

        //System.out.println("t   MERGE  END  " + t  );
    }



}
