package esempi.esempio2;

import java.util.Arrays;

public class OrderAlgorithms {

	public OrderAlgorithms() {
	}
	
	public int[] bubbleSort(int [] array) {
		System.out.println("Bubble sort");
	    for(int i = 0; i < array.length; i++) {
	        boolean flag = false;
	        for(int j = 0; j < array.length-1; j++) {
	            if(array[j]>array[j+1]) {
	                int k = array[j];
	                array[j] = array[j+1];
	                array[j+1] = k;
	                flag=true;
	            }
	        }
	        if(!flag) break;
	    }
	    return array;
	}
	
	
	public int[] selectionSort(int [] array) {
		System.out.println("Selection sort");
	    for(int i = 0; i < array.length-1; i++) {
	        int min = i;
	        for(int j = i+1; j < array.length; j++) {
	            if(array[min]>array[j]) {
	                min = j;
	            }
	        }
	        if(min!=i) { 
	            int k = array[min];
	            array[min]= array[i];
	            array[i] = k;
	        }
	    }
	    return array;
	}
	
	
	public int[] fakeSort(int[] array) {
		System.out.println("Fake sort");
		return array;
	}
	
	public int[] timeoutSort(int[] array){
		Arrays.sort(array);
		System.out.println("Timeout sort");
		while(true) {
			if (3<2) break;
		}
		return array; 
	}	
}
