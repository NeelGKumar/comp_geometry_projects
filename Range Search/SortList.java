/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author gkumar
 */
public class SortList {
    Asteroid[] sList;

    SortList(Asteroid[] list, char t){

        Asteroid[] temp = new Asteroid[list.length];
        System.arraycopy(list, 0, temp, 0, list.length);
        if (t == 'x'){
           // System.out.println("Xx SORT");
            sList = mergeSort(temp, 1);
        }
        else{
            //System.out.println("Yy SORT");
            sList = mergeSort(temp, 3);
        }

    }



    public Asteroid[] mergeSort (Asteroid[] list, int t){

        //System.out.println("t   MERGESORT  " + t  );

	if (list.length == 1) {
            //System.out.println("t   MERGESORT  END CASE 1  " + t  );
	    return list;
	}
	else {
	    Asteroid[] left = new Asteroid[list.length/2];
	    System.arraycopy(list, 0, left, 0, left.length);
	    Asteroid[] right = new Asteroid[list.length-left.length];
	    System.arraycopy(list, left.length, right, 0, right.length);
	    left = mergeSort(left, t);
	    right = mergeSort(right, t);
	    merge(left, right, list, t);
            //System.out.println("t   MERGESORT  END" + t  );
	    return list;
	}
    }
    private void merge(Asteroid[] left, Asteroid[] right, Asteroid[] list, int t) {
	int leftIndex = 0;
	int rightIndex = 0;
	int mergeIndex = 0;
        //System.out.println("t   MERGE  " + t  );
        double l = 0, r = 0;
	while (leftIndex < left.length && rightIndex < right.length) {

            if (t == 1){
                l = left[leftIndex].x;
                r = right[rightIndex].x;
                if ( l == r){
                    l = left[leftIndex].y;
                    r = right[rightIndex].y;
                }
            }
            else if (t==3){
                //System.out.println("Y SORT");
                l = left[leftIndex].y;
                r = right[rightIndex].y;
                if ( l == r){
                    l = left[leftIndex].x;
                    r = right[rightIndex].x;
                }
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

	Asteroid[] rest;
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
