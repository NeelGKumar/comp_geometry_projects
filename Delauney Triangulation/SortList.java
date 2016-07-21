


/**
 *
 * @author gkumar
 */
public class SortList {
    Edges[] sList;

    SortList(Edges[] list, char t){

        Edges[] temp = new Edges[list.length];
        System.arraycopy(list, 0, temp, 0, list.length);
        if (t == 'n'){
           // System.out.println("Xx SORT");
            sList = mergeSort(temp, 1);
        }
        else{
            //System.out.println("Yy SORT");
            sList = mergeSort(temp, 3);
        }

    }



    public Edges[] mergeSort (Edges[] list, int t){

        //System.out.println("t   MERGESORT  " + t  );

	if (list.length == 1) {
            //System.out.println("t   MERGESORT  END CASE 1  " + t  );
	    return list;
	}
	else {
	    Edges[] left = new Edges[list.length/2];
	    System.arraycopy(list, 0, left, 0, left.length);
	    Edges[] right = new Edges[list.length-left.length];
	    System.arraycopy(list, left.length, right, 0, right.length);
	    left = mergeSort(left, t);
	    right = mergeSort(right, t);
	    merge(left, right, list, t);
            //System.out.println("t   MERGESORT  END" + t  );
	    return list;
	}
    }
    private void merge(Edges[] left, Edges[] right, Edges[] list, int t) {
	int leftIndex = 0;
	int rightIndex = 0;
	int mergeIndex = 0;
        //System.out.println("t   MERGE  " + t  );
        double l = 0, r = 0;
	while (leftIndex < left.length && rightIndex < right.length) {

            if (t == 1){
                l = left[leftIndex].n;
                r = right[rightIndex].n;
                if ( l == r){
                    l = left[leftIndex].m;
                    r = right[rightIndex].m;
                }
            }
            else if (t==3){
                //System.out.println("Y SORT");
                l = left[leftIndex].m;
                r = right[rightIndex].m;
                if ( l == r){
                    l = left[leftIndex].n;
                    r = right[rightIndex].n;
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

	Edges[] rest;
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
