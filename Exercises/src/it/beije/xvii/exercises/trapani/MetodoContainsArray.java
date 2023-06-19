package it.beije.xvii.exercises.trapani;

public class MetodoContainsArray {
	protected static boolean contains(int e, int[] array) {
		boolean presente=false;
		for(int i=0; i<array.length; i++) {
			if(array[i]==e) {
				presente=true;
				break;
			}
		}
		return presente;	
	}

	
	public static void main(String[] args) {
		int[] num = {1,2,3,6,4,5};
		System.out.print(contains(1,num));		
	}
	

}
